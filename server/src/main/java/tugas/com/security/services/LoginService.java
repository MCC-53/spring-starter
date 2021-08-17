/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tugas.com.security.details.UserDetailService;
import tugas.com.security.models.User;
import tugas.com.security.models.dto.AuthResponse;
import tugas.com.security.models.dto.LoginRequest;
import tugas.com.security.repositories.UserRepository;

/**
 *
 * @author aceng
 */
@Service
public class LoginService {
    private UserDetailService userDetailService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserDetailService userDetailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public AuthResponse login(LoginRequest loginRequest){
        AuthResponse authResponse = new AuthResponse();
        User user = new User();
        user = userRepository.findByUsername(loginRequest.getUsername());

        if (user==null){
            throw new RuntimeException(String.format("Username '%s' not found",loginRequest.getUsername() ));
        }

        boolean isPasswordMatches  = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (isPasswordMatches==true){
            List<String> datarequest = userDetailService.loadUserByUsername(loginRequest.getUsername()).getAuthorities()
                    .stream()
                    .map(auth -> auth.getAuthority())
                    .collect(Collectors.toList());
            authResponse.setAuthorities(datarequest);
            return authResponse;
        }else {
            throw new UsernameNotFoundException("password tidak ditemukan");
        }
    }
}

