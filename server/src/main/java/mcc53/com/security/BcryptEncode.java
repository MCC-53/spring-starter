package mcc53.com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BcryptEncode {
    @Bean
    public BCryptPasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder();
    }
}
