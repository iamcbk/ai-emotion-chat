package ai.aichatbackend.service.impl;

import ai.aichatbackend.dto.ChatRequest;
import ai.aichatbackend.dto.ChatResponse;
import ai.aichatbackend.dto.EmotionAnalysisResult;
import ai.aichatbackend.entity.ChatMessage;
import ai.aichatbackend.entity.ChatSession;
import ai.aichatbackend.mapper.ChatMessageMapper;
import ai.aichatbackend.mapper.ChatSessionMapper;
import ai.aichatbackend.service.ChatService;
import ai.aichatbackend.service.EmotionAnalysisService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 聊天服务实现类
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@Service
public class ChatServiceImpl implements ChatService {
    
    // 手动声明日志对象
    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);
    
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final EmotionAnalysisService emotionAnalysisService;
    private final ChatSessionMapper chatSessionMapper;
    private final ChatMessageMapper chatMessageMapper;
    
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    
    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;
    
    @Value("${spring.ai.openai.chat.options.model}")
    private String model;
    
    @Value("${spring.ai.openai.chat.options.temperature:0.7}")
    private float temperature;
    
    public ChatServiceImpl(WebClient.Builder webClientBuilder,
                          ObjectMapper objectMapper,
                          EmotionAnalysisService emotionAnalysisService,
                          ChatSessionMapper chatSessionMapper,
                          ChatMessageMapper chatMessageMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
        this.emotionAnalysisService = emotionAnalysisService;
        this.chatSessionMapper = chatSessionMapper;
        this.chatMessageMapper = chatMessageMapper;
    }
    
    @Override
    @Transactional
    public ChatResponse processChat(ChatRequest request, String ipAddress) {
        // 1. 获取或创建会话
        ChatSession session = getOrCreateSession(request.getSessionUuid(), ipAddress);
        
        // 2. 保存用户消息
        ChatMessage userMessage = new ChatMessage();
        userMessage.setSessionId(session.getId());
        userMessage.setSender("user");
        userMessage.setContent(request.getContent());
        userMessage.setLanguage(request.getLanguage());
        userMessage.setIsDeleted(0);
        chatMessageMapper.insert(userMessage);
        
        // 3. 调用AI生成回复
        String aiReply = generateAiReply(session.getId(), request.getContent());
        
        // 4. 分析AI回复的情感
        EmotionAnalysisResult emotionResult = emotionAnalysisService.analyzeEmotion(aiReply);
        
        // 5. 保存AI回复
        ChatMessage aiMessage = new ChatMessage();
        aiMessage.setSessionId(session.getId());
        aiMessage.setSender("ai");
        aiMessage.setContent(aiReply);
        aiMessage.setSentiment(emotionResult.getSentiment());
        aiMessage.setTone(emotionResult.getTone());
        aiMessage.setEmotionScore(emotionResult.getEmotionScore());
        aiMessage.setLanguage(request.getLanguage());
        aiMessage.setIsDeleted(0);
        chatMessageMapper.insert(aiMessage);
        
        // 6. 更新会话最后活跃时间
        chatSessionMapper.updateLastActive(session.getId());
        
        // 7. 构建并返回响应
        return ChatResponse.builder()
                .messageId(aiMessage.getId())
                .content(aiReply)
                .sentiment(emotionResult.getSentiment())
                .tone(emotionResult.getTone())
                .emotionScore(emotionResult.getEmotionScore())
                .backgroundColor(emotionResult.getBackgroundColor())
                .timestamp(LocalDateTime.now())
                .build();
    }
    
    @Override
    public List<ChatMessage> getHistoryMessages(String sessionUuid) {
        ChatSession session = chatSessionMapper.findByUuid(sessionUuid);
        if (session == null) {
            return new ArrayList<>();
        }
        return chatMessageMapper.findBySessionId(session.getId());
    }
    
    @Override
    public boolean deleteMessage(Long messageId) {
        return chatMessageMapper.softDelete(messageId) > 0;
    }
    
    /**
     * 获取或创建聊天会话
     */
    private ChatSession getOrCreateSession(String sessionUuid, String ipAddress) {
        ChatSession session = chatSessionMapper.findByUuid(sessionUuid);
        if (session == null) {
            session = new ChatSession();
            session.setSessionUuid(sessionUuid);
            session.setIpAddress(ipAddress);
            chatSessionMapper.insert(session);
        }
        return session;
    }
    
    /**
     * 生成AI回复
     */
    private String generateAiReply(Long sessionId, String userInput) {
        try {
            // 获取历史对话作为上下文
            List<ChatMessage> historyMessages = chatMessageMapper.findBySessionId(sessionId);
            
            // 构建系统提示
            String systemPrompt = """
                    你是一个友好、专业、有趣的AI助手。
                    根据用户的情感和语气，灵活调整你的回复风格：
                    - 当用户情绪积极时，保持活泼和热情
                    - 当用户情绪消极时，提供共情和支持
                    - 当用户提问专业知识时，给出准确清晰的解答
                    - 当谈话轻松时，可以适当幽默
                    
                    请记住保持对话的连贯性和上下文理解。
                    尽量简洁，避免冗余和重复。
                    """;
            
            // 构建OpenAI API请求
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("model", model);
            requestBody.put("temperature", temperature);
            
            ArrayNode messagesArray = objectMapper.createArrayNode();
            
            // 添加系统消息
            ObjectNode systemMessage = objectMapper.createObjectNode();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messagesArray.add(systemMessage);
            
            // 添加最近的10条历史消息作为上下文
            if (!historyMessages.isEmpty()) {
                List<ChatMessage> recentMessages = historyMessages.stream()
                        .skip(Math.max(0, historyMessages.size() - 10))
                        .collect(Collectors.toList());
                
                for (ChatMessage msg : recentMessages) {
                    ObjectNode messageNode = objectMapper.createObjectNode();
                    if ("user".equals(msg.getSender())) {
                        messageNode.put("role", "user");
                    } else {
                        messageNode.put("role", "assistant");
                    }
                    messageNode.put("content", msg.getContent());
                    messagesArray.add(messageNode);
                }
            }
            
            // 添加当前用户输入
            ObjectNode userMessage = objectMapper.createObjectNode();
            userMessage.put("role", "user");
            userMessage.put("content", userInput);
            messagesArray.add(userMessage);
            
            requestBody.set("messages", messagesArray);
            
            // 打印请求体以便调试
            String requestBodyStr = requestBody.toString();
            log.debug("OpenAI API请求：{}", requestBodyStr);
            
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
            log.debug("OpenAI API响应：{}", responseJson);
            
            // 检查响应是否为HTML (通常表示错误)
            if (responseJson != null && responseJson.trim().startsWith("<!")) {
                log.error("OpenAI API返回了HTML而不是JSON: {}", responseJson.substring(0, Math.min(200, responseJson.length())));
                return "非常抱歉，AI服务暂时不可用。请检查API配置或稍后再试。";
            }
            
            // 解析响应
            JsonNode response = objectMapper.readTree(responseJson);
            if (response.has("error")) {
                log.error("OpenAI API返回错误: {}", response.path("error").path("message").asText());
                return "AI服务返回错误：" + response.path("error").path("message").asText();
            }
            
            return response.path("choices").path(0).path("message").path("content").asText();
            
        } catch (Exception e) {
            log.error("生成AI回复失败", e);
            return "很抱歉，我遇到了一些技术问题，无法正常回复。请稍后再试。错误类型：" + e.getClass().getSimpleName();
        }
    }
} 