/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author putug
 */
@Configuration
public class corsConfig implements WebMvcConfigurer {
    
    private final String ENDPOINT = "/**";
    private final String[] ALLOWEDMETHOD = {"GET", "POST", "DELETE", "PUT"};
    private final String[] ORIGINS = {"http://localhost:8081"};
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping(ENDPOINT)
                .allowedMethods(ALLOWEDMETHOD)
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOrigins(ORIGINS);
    }
    
}
