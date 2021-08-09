/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author WahyuKu
 */
@Configuration
public class CounterConfig {
    
    @Bean
    public Counter counter() {
        return new Counter();
    }
}
