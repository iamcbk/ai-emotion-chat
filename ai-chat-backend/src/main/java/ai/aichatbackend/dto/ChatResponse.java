package ai.aichatbackend.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 聊天响应
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private Long messageId;
    private String content;
    private String sentiment;
    private String tone;
    private BigDecimal emotionScore;
    private String backgroundColor;
    private LocalDateTime timestamp;
    
    public static ChatResponseBuilder builder() {
        return new ChatResponseBuilder();
    }
    
    public static class ChatResponseBuilder {
        private Long messageId;
        private String content;
        private String sentiment;
        private String tone;
        private BigDecimal emotionScore;
        private String backgroundColor;
        private LocalDateTime timestamp;
        
        ChatResponseBuilder() {}
        
        public ChatResponseBuilder messageId(Long messageId) {
            this.messageId = messageId;
            return this;
        }
        
        public ChatResponseBuilder content(String content) {
            this.content = content;
            return this;
        }
        
        public ChatResponseBuilder sentiment(String sentiment) {
            this.sentiment = sentiment;
            return this;
        }
        
        public ChatResponseBuilder tone(String tone) {
            this.tone = tone;
            return this;
        }
        
        public ChatResponseBuilder emotionScore(BigDecimal emotionScore) {
            this.emotionScore = emotionScore;
            return this;
        }
        
        public ChatResponseBuilder backgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }
        
        public ChatResponseBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        
        public ChatResponse build() {
            ChatResponse chatResponse = new ChatResponse();
            chatResponse.messageId = this.messageId;
            chatResponse.content = this.content;
            chatResponse.sentiment = this.sentiment;
            chatResponse.tone = this.tone;
            chatResponse.emotionScore = this.emotionScore;
            chatResponse.backgroundColor = this.backgroundColor;
            chatResponse.timestamp = this.timestamp;
            return chatResponse;
        }
    }
} 