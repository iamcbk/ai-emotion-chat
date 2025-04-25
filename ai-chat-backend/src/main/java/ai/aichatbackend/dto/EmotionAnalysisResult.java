package ai.aichatbackend.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 表情分析结果
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionAnalysisResult {
    private String sentiment; // positive, negative, neutral
    private String tone; // gentle, serious, humorous等
    private BigDecimal emotionScore; // 0.000 - 1.000
    private String backgroundColor; // 背景颜色代码，如 #E6F7FF
    
    public String getSentiment() {
        return sentiment;
    }
    
    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
    
    public String getTone() {
        return tone;
    }
    
    public void setTone(String tone) {
        this.tone = tone;
    }
    
    public BigDecimal getEmotionScore() {
        return emotionScore;
    }
    
    public void setEmotionScore(BigDecimal emotionScore) {
        this.emotionScore = emotionScore;
    }
    
    public String getBackgroundColor() {
        return backgroundColor;
    }
    
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    // Builder实现，移除Lombok的@Builder注解，完全手动实现
    public static EmotionAnalysisResultBuilder builder() {
        return new EmotionAnalysisResultBuilder();
    }
    
    public static class EmotionAnalysisResultBuilder {
        private String sentiment;
        private String tone;
        private BigDecimal emotionScore;
        private String backgroundColor;
        
        EmotionAnalysisResultBuilder() {}
        
        public EmotionAnalysisResultBuilder sentiment(String sentiment) {
            this.sentiment = sentiment;
            return this;
        }
        
        public EmotionAnalysisResultBuilder tone(String tone) {
            this.tone = tone;
            return this;
        }
        
        public EmotionAnalysisResultBuilder emotionScore(BigDecimal emotionScore) {
            this.emotionScore = emotionScore;
            return this;
        }
        
        public EmotionAnalysisResultBuilder backgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }
        
        public EmotionAnalysisResult build() {
            EmotionAnalysisResult result = new EmotionAnalysisResult();
            result.sentiment = this.sentiment;
            result.tone = this.tone;
            result.emotionScore = this.emotionScore;
            result.backgroundColor = this.backgroundColor;
            return result;
        }
    }
} 