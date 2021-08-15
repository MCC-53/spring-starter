/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.services;

import java.util.List;
import java.util.stream.Collectors;
import mcc53.com.front.models.AuthResponse;
import mcc53.com.front.models.Login;
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
 * @author Xvitas
 */
@Service
public class LoginService {

    RestTemplate restTemplate;

    @Autowired
    LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.baseUrl}/login")
    private String baseUrl;

    public AuthResponse loginRequest(Login login) {

        ResponseEntity<AuthResponse> res = restTemplate.postForEntity(baseUrl, login, AuthResponse.class);
        System.out.println(res);
        try {
            List<GrantedAuthority> authorities = res.getBody().getAuth()
                    .stream()
                    .map(auth -> new SimpleGrantedAuthority(auth))
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authToken
                    = new UsernamePasswordAuthenticationToken(login.getUsername(),
                            login.getPassword(), authorities);

            SecurityContextHolder.getContext().setAuthentication(authToken);

            return res.getBody();
        } catch (Exception e) {
            return res.getBody();

        }
    }
}
