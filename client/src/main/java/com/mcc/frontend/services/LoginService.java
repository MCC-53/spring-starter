/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.frontend.services;

import com.mcc.frontend.models.AuthResponse;
import com.mcc.frontend.models.Login;
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
 * @author HP
 */
@Service
public class LoginService {
    RestTemplate restTemplate;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Value("${api.serverURL}/login")
    private String baseUrl;

    public AuthResponse loginRequest(Login login) {
        ResponseEntity<AuthResponse> respon = restTemplate.postForEntity(baseUrl, login, AuthResponse.class);

        List<GrantedAuthority> authorities
                = respon.getBody().getAuthoritiesResponse()
                        .stream()
                        .map(auth -> new SimpleGrantedAuthority(auth))
                        .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), authorities);

        SecurityContextHolder.getContext().setAuthentication(authToken);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        return respon.getBody();
    }
}
