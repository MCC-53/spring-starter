/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.config;

import com.MCC53.springsecurity.details.AppUserDetailsService;
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
 * @author user
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

@Autowired
PasswordEncoder passwordEncoder;

@Autowired
AppUserDetailsService appUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    
        System.out.println(passwordEncoder.encode("admin"));
        auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder);
    
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
       http.csrf().disable().authorizeRequests()
               
//               .antMatchers("/employee")
//               .hasAnyRole("ADMIN", "EMPLOYEE")
//             
               //appclientcoba
               .antMatchers("/employee/**").permitAll()
//               .antMatchers("/department/**").permitAll()
               .antMatchers("/project/**").permitAll()
               
               .antMatchers("/login/**","/register/**","/email").permitAll()
               .anyRequest()
               .authenticated()
               .and()
//               .formLogin()
               .httpBasic();
    
       http.cors();
    }




}
