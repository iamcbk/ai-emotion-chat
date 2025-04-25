package ai.aichatbackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ChatMessage实体类
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private Long id;
    private Long sessionId;
    private String sender;
    private String content;
    private String sentiment;
    private String tone;
    private BigDecimal emotionScore;
    private String language;
    private Integer isDeleted;
    private LocalDateTime createdAt;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
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
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public Integer getIsDeleted() {
        return isDeleted;
    }
    
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 