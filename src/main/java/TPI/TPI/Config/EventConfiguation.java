package TPI.TPI.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Configuration
public class EventConfiguation {
    @Bean
    public SseEmitter sseEmitter() {
        return new SseEmitter();
    }
}
