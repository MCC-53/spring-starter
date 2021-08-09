package com.nieceoftimes.serverside.auth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private final String[] DOMAINS = {"http://localhost:8030"};
    private final String[] HTTPMETHODS = {"GET", "POST", "PUT", "DELETE"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(DOMAINS)
                .allowedMethods(HTTPMETHODS)
                .allowCredentials(true);

    }
}
