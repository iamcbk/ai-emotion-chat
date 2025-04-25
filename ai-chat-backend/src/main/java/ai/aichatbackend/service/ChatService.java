package ai.aichatbackend.service;

import ai.aichatbackend.dto.ChatRequest;
import ai.aichatbackend.dto.ChatResponse;
import ai.aichatbackend.entity.ChatMessage;

import java.util.List;

/**
 * 聊天服务接口
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
public interface ChatService {
    
    /**
     * 处理用户聊天请求并返回AI响应
     */
    ChatResponse processChat(ChatRequest request, String ipAddress);
    
    /**
     * 获取指定会话的历史消息
     */
    List<ChatMessage> getHistoryMessages(String sessionUuid);
    
    /**
     * 删除消息
     */
    boolean deleteMessage(Long messageId);
} 