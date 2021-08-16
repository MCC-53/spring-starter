package com.coba.client.services;

import com.coba.client.models.AuthResponse;
import com.coba.client.models.Login;
import com.coba.client.models.ResponseSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService {
    RestTemplate restTemplate;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.Url}/auth/login")
    private String apiUrl;

    public ResponseSingle<AuthResponse> loginRequest(Login login){
        System.out.println(login);
            ResponseEntity<ResponseSingle<AuthResponse>> respon = restTemplate.exchange(apiUrl, HttpMethod.POST, new HttpEntity<>(login),
                    new ParameterizedTypeReference<ResponseSingle<AuthResponse>>() {
                    });
            List<GrantedAuthority> authorities = respon.getBody().getData().getAuthorities()
                    .stream()
                    .map(auth -> new SimpleGrantedAuthority(auth))
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), authorities);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
             return respon.getBody();
    }
}
