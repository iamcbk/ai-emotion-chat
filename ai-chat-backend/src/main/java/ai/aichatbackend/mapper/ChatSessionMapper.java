package ai.aichatbackend.mapper;

import ai.aichatbackend.entity.ChatSession;
import org.apache.ibatis.annotations.*;

/**
 * @date 205/04/25
 * @author 蔡炳堃
 * @email 3509214013@qq.com
 * @description 欢迎大家一起学习交流
 */
@Mapper
public interface ChatSessionMapper {
    
    @Insert("INSERT INTO chat_session(session_uuid, ip_address) VALUES(#{sessionUuid}, #{ipAddress})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ChatSession session);
    
    @Select("SELECT * FROM chat_session WHERE session_uuid = #{sessionUuid}")
    ChatSession findByUuid(String sessionUuid);
    
    @Update("UPDATE chat_session SET last_active = NOW() WHERE id = #{id}")
    int updateLastActive(Long id);
    
    @Select("SELECT * FROM chat_session WHERE id = #{id}")
    ChatSession findById(Long id);
} 