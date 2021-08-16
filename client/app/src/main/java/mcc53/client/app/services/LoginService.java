/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;
import java.util.List;
import java.util.stream.Collectors;
import mcc53.client.app.models.AuthResponse;
import mcc53.client.app.models.LoginData;
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
 * @author ACER
 */
@Service
public class LoginService {
    private RestTemplate restTemplate;
    @Value("${api.baseUrl}/users/users-login")
    private String baseUrl;
    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    } public AuthResponse login(LoginData loginData) {
        ResponseEntity<AuthResponse> responseEntity = restTemplate.postForEntity(baseUrl, loginData, AuthResponse.class);
        List<GrantedAuthority> authorities = responseEntity.getBody().getAuthoritiesResponse()
                .stream()
                .map(auth -> new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        return responseEntity.getBody();
    }
}