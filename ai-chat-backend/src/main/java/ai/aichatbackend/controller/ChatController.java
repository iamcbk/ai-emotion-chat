package ai.aichatbackend.controller;

import ai.aichatbackend.dto.ChatRequest;
import ai.aichatbackend.dto.ChatResponse;
import ai.aichatbackend.entity.ChatMessage;
import ai.aichatbackend.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 聊天控制器
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {
    
    // 手动声明日志对象
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    
    private final ChatService chatService;
    
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    
    /**
     * 发送消息并获取AI回复
     */
    @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatResponse> sendMessage(@Valid @RequestBody ChatRequest request, 
                                                  HttpServletRequest httpRequest) {
        String ipAddress = getClientIpAddress(httpRequest);
        log.info("接收到来自IP：{}的聊天请求，sessionUuid: {}", ipAddress, request.getSessionUuid());
        
        ChatResponse response = chatService.processChat(request, ipAddress);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取会话历史消息
     */
    @GetMapping(value = "/history/{sessionUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChatMessage>> getHistoryMessages(@PathVariable String sessionUuid) {
        log.info("获取会话历史, sessionUuid: {}", sessionUuid);
        
        List<ChatMessage> messages = chatService.getHistoryMessages(sessionUuid);
        return ResponseEntity.ok(messages);
    }
    
    /**
     * 删除消息
     */
    @DeleteMapping(value = "/message/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteMessage(@PathVariable Long messageId) {
        log.info("删除消息, messageId: {}", messageId);
        
        boolean result = chatService.deleteMessage(messageId);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 获取客户端IP地址
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader != null && !xForwardedForHeader.isEmpty()) {
            return xForwardedForHeader.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
} 