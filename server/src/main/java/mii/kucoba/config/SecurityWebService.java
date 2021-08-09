/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author haikal
 */
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityWebService extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
//    @Bean
//     AuthenticationProvider authenticationProvider() {
//     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//     provider.setUserDetailsService(userDetailsService);
//     provider.setPasswordEncoder(new BCryptPasswordEncoder());
//     return provider;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/emp/**", "/user/**", "/login/**","/project/**","/department/**","/email/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http.cors();
    }
     
     
//                .antMatchers("/login").permitAll()
////                .antMatchers(HttpMethod.GET, "/department")
////                .hasAuthority("READ_DEPARTMENT")
//                .antMatchers(HttpMethod.POST, "/department")
//                .hasAuthority("CREATE_DEPARTMENT")
//                .antMatchers("/home")
//                .hasAuthority("READ_HOME")
//                .antMatchers("/user")
//                .hasAnyRole("ADMIN","USER")
    
}
