package ai.aichatbackend.service;

import ai.aichatbackend.dto.EmotionAnalysisResult;

/**
 * 情感分析服务接口
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
public interface EmotionAnalysisService {
    
    /**
     * 分析文本情感，并返回情感分析结果
     * @param text 待分析文本
     * @return 情感分析结果，包含情感分类、语气和情感强度等
     */
    EmotionAnalysisResult analyzeEmotion(String text);
} 