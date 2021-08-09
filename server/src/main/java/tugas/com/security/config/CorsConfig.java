package tugas.com.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private final String[] DOMAINS = {"http://localhost:8082"};
    private final String[] METHODS = {"GET", "PUT", "POST", "DELETE"};

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins(DOMAINS)
                .allowedMethods(METHODS)
                .allowCredentials(true);
    }
}
