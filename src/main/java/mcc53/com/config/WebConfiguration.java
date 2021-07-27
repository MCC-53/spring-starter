/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 *
 * @author WahyuKu
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public WebConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/department")
                .hasAnyRole("ADMIN", "EMPLOYEE")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        List<UserDetails> users =  new ArrayList<>();
        
        UserDetails user1 = User.builder()
                .username("uzumaki")
                .password(passwordEncoder.encode("admin"))
                .authorities(authorities())
                .build();
        
        UserDetails user2 = User.builder()
                .username("uchiha")
                .password(passwordEncoder.encode("admin"))
                .roles("EMPLOYEE")
                .build();
        
        users.add(user1);
        users.add(user2);
        
        return new InMemoryUserDetailsManager(users);
    }
    
    private Collection<GrantedAuthority> authorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("READ_DEPARTMENT"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }
}
