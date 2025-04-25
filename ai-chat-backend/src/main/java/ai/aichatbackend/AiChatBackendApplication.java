package ai.aichatbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("ai.aichatbackend.mapper")
public class AiChatBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiChatBackendApplication.class, args);
    }

}
