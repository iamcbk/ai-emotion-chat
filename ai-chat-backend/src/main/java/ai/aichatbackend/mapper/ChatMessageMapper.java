package ai.aichatbackend.mapper;

import ai.aichatbackend.entity.ChatMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@Mapper
public interface ChatMessageMapper {
    
    @Insert("INSERT INTO chat_message(session_id, sender, content, sentiment, tone, emotion_score, language) " +
            "VALUES(#{sessionId}, #{sender}, #{content}, #{sentiment}, #{tone}, #{emotionScore}, #{language})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ChatMessage message);
    
    @Select("SELECT * FROM chat_message WHERE session_id = #{sessionId} AND is_deleted = 0 ORDER BY created_at ASC")
    List<ChatMessage> findBySessionId(Long sessionId);
    
    @Select("SELECT * FROM chat_message WHERE id = #{id}")
    ChatMessage findById(Long id);
    
    @Update("UPDATE chat_message SET is_deleted = 1 WHERE id = #{id}")
    int softDelete(Long id);
} 