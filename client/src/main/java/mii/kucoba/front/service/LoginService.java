/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.front.service;

import java.util.List;
import java.util.stream.Collectors;
import mii.kucoba.front.models.request.AuthenticationRole;
import mii.kucoba.front.models.request.Login;
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
 * @author abiyo
 */
@Service
public class LoginService {

    RestTemplate restTemplate;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.backend}/login")
    private String baseUrl;

    public String loginProcessService(Login l) {
        try {
            ResponseEntity<AuthenticationRole> data = restTemplate.postForEntity(baseUrl, l, AuthenticationRole.class);

            List<GrantedAuthority> authorities
                    = data.getBody().getAuth()
                            .stream()
                            .map(auth -> new SimpleGrantedAuthority(auth))
                            .collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authToken
                    = new UsernamePasswordAuthenticationToken(l.getUsername(), l.getPassword(), authorities);

            SecurityContextHolder.getContext().setAuthentication(authToken);
            return "Success";
        } catch (Exception e) {
            return "Gagal";
        }
    }

}
