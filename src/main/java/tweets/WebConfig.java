package tweets;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // CORS를 모든 엔드포인트에 대해 설정
        registry.addMapping("/**")  // 모든 경로에 대해 CORS를 허용
                .allowedOrigins("http://localhost:3000")  // 허용할 프론트엔드 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메서드
                .allowCredentials(true)  // 인증 정보(Cookie 등)를 포함한 요청을 허용
                .allowedHeaders("*");  // 모든 헤더를 허용
    }
}

