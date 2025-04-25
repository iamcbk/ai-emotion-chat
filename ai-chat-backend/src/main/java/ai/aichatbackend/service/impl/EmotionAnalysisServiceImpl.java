package ai.aichatbackend.service.impl;

import ai.aichatbackend.dto.EmotionAnalysisResult;
import ai.aichatbackend.service.EmotionAnalysisService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现EmotionAnalysisService接口
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@Service
public class EmotionAnalysisServiceImpl implements EmotionAnalysisService {
    
    // 手动声明日志对象
    private static final Logger log = LoggerFactory.getLogger(EmotionAnalysisServiceImpl.class);
    
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    
    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;
    
    @Value("${spring.ai.openai.chat.options.model}")
    private String model;
    
    public EmotionAnalysisServiceImpl(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }
    
    @Override
    public EmotionAnalysisResult analyzeEmotion(String text) {
        try {
            String systemPrompt = """
                    你是一个专业的情感分析AI助手。你需要基于用户输入的文本进行情感分析，并返回以下信息：
                    1. sentiment：情感分类，只能是positive（积极）、negative（消极）或neutral（中性）之一
                    2. tone：基于文本的语气风格，如gentle（温和）、serious（严肃）、humorous（幽默）等
                    3. emotionScore：情感强度分值，0.000代表中性，1.000代表情感非常强烈（无论是积极还是消极）
                    4. backgroundColor：根据情感推荐的背景颜色，使用HEX格式，如：
                       - 积极情感：从浅蓝色(#E6F7FF)到亮绿色(#E6FFE6)
                       - 消极情感：从浅粉色(#FFE6E6)到浅紫色(#F0E6FF)
                       - 中性情感：从浅灰(#F5F5F5)到米色(#F5F0E6)
                       色彩深浅应与emotionScore相关
                       
                    请确保分析结果的格式为JSON对象。
                    """;
            
            // 构建请求体
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("model", model);
            
            ArrayNode messagesArray = objectMapper.createArrayNode();
            
            ObjectNode systemMessage = objectMapper.createObjectNode();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messagesArray.add(systemMessage);
            
            ObjectNode userMessage = objectMapper.createObjectNode();
            userMessage.put("role", "user");
            userMessage.put("content", text);
            messagesArray.add(userMessage);
            
            requestBody.set("messages", messagesArray);
            
            // 添加结构化输出配置
            ObjectNode responseFormatNode = objectMapper.createObjectNode();
            responseFormatNode.put("type", "json_object");
            requestBody.set("response_format", responseFormatNode);
            
            requestBody.put("temperature", 0.7);
            
            // 打印请求体以便调试
            String requestBodyStr = requestBody.toString();
            log.debug("情感分析API请求：{}", requestBodyStr);
            
            // 发送请求到OpenAI API
            String responseJson = webClient.post()
                    .uri(baseUrl + "/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("User-Agent", "AIChatBackend/1.0")
                    .bodyValue(requestBodyStr)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            // 先记录响应以便调试
            log.debug("情感分析API响应：{}", responseJson);
            
            // 检查响应是否为HTML (通常表示错误)
            if (responseJson != null && responseJson.trim().startsWith("<!")) {
                log.error("情感分析API返回了HTML而不是JSON: {}", responseJson.substring(0, Math.min(200, responseJson.length())));
                return getDefaultResult();
            }
            
            // 解析响应
            JsonNode response = objectMapper.readTree(responseJson);
            
            // 检查错误
            if (response.has("error")) {
                log.error("情感分析API返回错误: {}", response.path("error").path("message").asText());
                return getDefaultResult();
            }
            
            String content = response.path("choices").path(0).path("message").path("content").asText();
            log.debug("情感分析内容: {}", content);
            
            if (content == null || content.isEmpty()) {
                log.error("情感分析API返回内容为空");
                return getDefaultResult();
            }
            
            // 处理可能的非JSON响应
            try {
                // 使用TypeReference增加类型安全性
                Map<String, Object> resultMap = objectMapper.readValue(content, 
                        new TypeReference<Map<String, Object>>() {});
                
                // 类型安全的获取和转换
                String sentiment = (String) resultMap.get("sentiment");
                String tone = (String) resultMap.get("tone");
                String backgroundColor = (String) resultMap.get("backgroundColor");
                
                // 安全转换数值
                BigDecimal emotionScore;
                Object scoreObj = resultMap.get("emotionScore");
                if (scoreObj instanceof Number) {
                    emotionScore = new BigDecimal(scoreObj.toString());
                } else if (scoreObj instanceof String) {
                    emotionScore = new BigDecimal((String) scoreObj);
                } else {
                    emotionScore = BigDecimal.valueOf(0.5); // 默认值
                }
                
                // 确保所有值不为null
                if (sentiment == null) sentiment = "neutral";
                if (tone == null) tone = "neutral";
                if (backgroundColor == null) backgroundColor = "#F5F5F5";
                
                // 构建结果
                return EmotionAnalysisResult.builder()
                        .sentiment(sentiment)
                        .tone(tone)
                        .emotionScore(emotionScore)
                        .backgroundColor(backgroundColor)
                        .build();
                
            } catch (Exception e) {
                log.error("情感分析结果解析失败: {}", e.getMessage());
                return getDefaultResult();
            }
            
        } catch (Exception e) {
            log.error("情感分析失败", e);
            return getDefaultResult();
        }
    }

    // 提供默认的分析结果
    private EmotionAnalysisResult getDefaultResult() {
        return EmotionAnalysisResult.builder()
                .sentiment("neutral")
                .tone("neutral")
                .emotionScore(new BigDecimal("0.5"))
                .backgroundColor("#F5F5F5")
                .build();
    }
} 