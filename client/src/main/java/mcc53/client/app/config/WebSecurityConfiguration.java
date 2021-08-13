/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author haikal
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
               
               .antMatchers("/css/**","/js/**","/lib/**","/assets/**").permitAll()
               
               .antMatchers("/login").permitAll()
               .antMatchers("/dashboard").authenticated()
               .anyRequest()
               .authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .loginProcessingUrl("/login")
               .defaultSuccessUrl("/dashboard")
               .failureForwardUrl("/login?error=true")
               .permitAll()
               .and()
               .logout()
               .logoutUrl("/logout");
    }
    
    
    
}
