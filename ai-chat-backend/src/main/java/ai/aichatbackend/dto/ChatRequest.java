package ai.aichatbackend.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * 聊天请求
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@Data
public class ChatRequest {
    @NotBlank(message = "会话ID不能为空")
    private String sessionUuid;
    
    @NotBlank(message = "消息内容不能为空")
    private String content;
    
    private String language = "zh";
    
    public String getSessionUuid() {
        return sessionUuid;
    }
    
    public void setSessionUuid(String sessionUuid) {
        this.sessionUuid = sessionUuid;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
} 