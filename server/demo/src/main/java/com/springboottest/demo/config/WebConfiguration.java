/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.config;
import com.springboottest.demo.services.AppUserDetailService;
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
 * @author ACER
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter {
    PasswordEncoder passwordEncoder;
    AppUserDetailService appUserDetailService;
    @Autowired
    public WebConfiguration(PasswordEncoder passwordEncoder, AppUserDetailService appUserDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailService = appUserDetailService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailService).passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/employees", "/departments", "/projects", "/employees-department", "/employees-project", "/users", "/")
                .permitAll()
                /*.hasRole("ADMIN")
                .antMatchers("/employees/get-employees-by-username")
                .hasAnyRole("ADMIN", "EMPLOYEE")*/
                .anyRequest()
                .permitAll()
                /*.authenticated()
                .and()
                .httpBasic()*/;
    }
}