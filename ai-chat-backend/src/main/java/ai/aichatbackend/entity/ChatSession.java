package ai.aichatbackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * ChatSession实体类
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatSession {
    private Long id;
    private String sessionUuid;
    private LocalDateTime createdAt;
    private LocalDateTime lastActive;
    private String ipAddress;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSessionUuid() {
        return sessionUuid;
    }
    
    public void setSessionUuid(String sessionUuid) {
        this.sessionUuid = sessionUuid;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getLastActive() {
        return lastActive;
    }
    
    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
} 