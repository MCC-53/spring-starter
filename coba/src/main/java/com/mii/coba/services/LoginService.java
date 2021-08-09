/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.services;

import com.mii.coba.detail.UserDetailService;
import com.mii.coba.models.User;
import com.mii.coba.models.request.AuthResponse;
import com.mii.coba.models.request.LoginRequest;
import com.mii.coba.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class LoginService {
    
    private PasswordEncoder passwordEncoder;
    
    private UserRepository userRepository;
        
    private UserDetailService userDetailService;

    @Autowired
    public LoginService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserDetailService userDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userDetailService = userDetailService;
    }
    
    public AuthResponse prosesLogin(LoginRequest loginRequest){
        
        AuthResponse authResponse = new AuthResponse();
        
        User user = new User();
        
        user = userRepository.findByUsername(loginRequest.getUsername());
        
        if(user == null){
            throw new UsernameNotFoundException("Username tidak ditemukan");
        }
        
        boolean password = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if(password == true){
            String dat = userDetailService.loadUserByUsername(loginRequest.getUsername()).getAuthorities().toString();
            List<String> data = new ArrayList<>();
//                data.add(user.getUsername());
//                data.add(user.getPassword());
                data.add(dat);
                authResponse.setAuth(data);
                return authResponse;
        }else{
            throw new UsernameNotFoundException("password tidak ditemukan");
        }
    }
}
