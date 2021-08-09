package com.mii.clinetapp.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
@Data
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
