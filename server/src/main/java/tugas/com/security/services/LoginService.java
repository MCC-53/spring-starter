/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.services;

import java.util.ArrayList;
import java.util.List;
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
        AuthResponse loginResponse = new AuthResponse();
        User user = new User();
        user = userRepository.findByUsername(loginRequest.getUsername());
        
        if(user == null){
            throw new RuntimeException(String.format("Username '%s' not found", loginRequest.getUsername())); 
        }
        boolean isPasswordMatches = passwordEncoder.matches(loginRequest.getPassword(),user.getPassword());
        
        if(isPasswordMatches == true){
            String data = userDetailService.loadUserByUsername(loginRequest.getUsername()).getAuthorities().toString();
            List<String> data2 = new ArrayList<>();
            data2.add(user.getUsername());
            data2.add(data);
            loginResponse.setAuthorities(data2);
            return loginResponse;
        }else{
            throw new UsernameNotFoundException("password tidak ditemukan");
        }
    }
}

