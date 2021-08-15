/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.app.client.clientapp.services;

import java.util.List;
import java.util.stream.Collectors;
import mcc53.app.client.clientapp.models.AuthResponse;
import mcc53.app.client.clientapp.models.Login;
import mcc53.app.client.clientapp.models.ResponseSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author firmanzega
 */
@Service
public class LoginService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.baseURL}/auth/login")
    private String baseUrl;

    public ResponseSingle<AuthResponse> loginRequest(Login login) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<Login> httpEntity = new HttpEntity<>(login, headers);

        ResponseEntity<ResponseSingle<AuthResponse>> response
                = restTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity,
                        new ParameterizedTypeReference<ResponseSingle<AuthResponse>>() {});
        
        List<GrantedAuthority> authorities = response.getBody()
                .getPayLoad().getAuthorities().stream()
                .map(auth -> new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
        System.out.println("this authorities : " +  authorities);
        
        UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), authorities);
        
        SecurityContextHolder.getContext().setAuthentication(authToken);
        
//        System.out.println("this token : "+ authToken);
        
//        System.out.println(SecurityContextHolder.getContext().toString());
        return response.getBody();
    }

}
