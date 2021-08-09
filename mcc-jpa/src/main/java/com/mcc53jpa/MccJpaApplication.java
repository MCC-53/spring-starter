package com.mcc53jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MccJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MccJpaApplication.class, args);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("admin");

        System.out.println(encodedPassword);
    }

}
