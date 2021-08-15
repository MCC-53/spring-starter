/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mcc53.ClientApp.Services;

import Mcc53.ClientApp.Models.User;
import Mcc53.ClientApp.ViewModels.LoginForm;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author putug
 */
@Service
public class AuthService {
    @Value("${api.mcc53serverSideUrl}/auth")
    private String _serverUrl;
    private RestTemplate _restTemplate;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        _restTemplate = restTemplate;
    }
    
    public User login(LoginForm loginForm){
        String endpoint = String.format("%s/login", _serverUrl);
        HttpEntity<LoginForm> request = new HttpEntity(loginForm);
        ResponseEntity<User> response = _restTemplate.exchange(
                endpoint, 
                HttpMethod.POST, 
                request, 
                User.class);
        
        User user = response.getBody();
        List<GrantedAuthority> permissions = user.getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission))
                .collect(Collectors.toList());
        
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginForm.getUsername(), 
                loginForm.getPassword(), 
                permissions);
        
        SecurityContextHolder.getContext().setAuthentication(token);
        
        return user;
    }
}
