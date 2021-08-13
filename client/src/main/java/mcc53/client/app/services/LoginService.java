/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;

import java.util.List;
import java.util.stream.Collectors;
import mcc53.client.app.models.AuthResponse;
import mcc53.client.app.models.Login;
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
 * @author haikal
 */
@Service
public class LoginService {
    RestTemplate restTemplate;
    
    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Value("${api.Url}/login")
    private String Url;
    
    public AuthResponse loginRequest(Login login) {
        ResponseEntity<AuthResponse> res = restTemplate.postForEntity(Url, login, AuthResponse.class);
                
        List<GrantedAuthority> authorities =
                res.getBody().getAuthorities()
                .stream()
                .map(auth -> new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
        
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), authorities);
        
        SecurityContextHolder.getContext().setAuthentication(authToken);
        
        return res.getBody();                
    }
    
    
    
}
