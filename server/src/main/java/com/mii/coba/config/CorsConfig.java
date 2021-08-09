/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author HP
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer{
    private final String[] DOMAINS = {"http://localhost:8084"};
    private final String[] METHODS = {"GET","PUT","POST","DELETE"};
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(DOMAINS)
                .allowedMethods(METHODS)
                .allowCredentials(true);
    }
}
