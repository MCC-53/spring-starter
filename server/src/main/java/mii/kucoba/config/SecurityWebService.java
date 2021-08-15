/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.config;

import mii.kucoba.Detail.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author abiyo
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityWebService extends WebSecurityConfigurerAdapter{
       
    private AppUserDetailService detailService;
    private PasswordEncoder passwordEncoder;
    
    
    
    @Autowired
    public SecurityWebService(PasswordEncoder passwordEncoder,
            AppUserDetailService detailService) {
        this.passwordEncoder = passwordEncoder;
        this.detailService = detailService;
    }

    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(passwordEncoder.encode("admin"));
        auth.userDetailsService(detailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/role","/privilege","/user").hasRole("ADMIN")
                .antMatchers("/department","/emp/**").hasRole("ADMIN")
                .antMatchers("/login","/project/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        
    }
    
    
    
}