/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.client.clientapp.services;

import com.MCC53.client.clientapp.models.AuthResponse;
import com.MCC53.client.clientapp.models.Login;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Service
public class LoginService {

    RestTemplate restTemplate;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.baseUrl}/login")
    private String baseUrl;

    public AuthResponse loginRequest(Login login) {

        ResponseEntity<AuthResponse> respon = restTemplate.postForEntity(baseUrl, login, AuthResponse.class);
        
//        System.out.println(respon.getBody());
       
        List<GrantedAuthority> authorities
                = respon.getBody().getAuthorities()
                        .stream()
                        .map(auth -> new SimpleGrantedAuthority(auth))
                        .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), authorities);

        SecurityContextHolder.getContext().setAuthentication(authToken);
        
        return respon.getBody();
    }
}
