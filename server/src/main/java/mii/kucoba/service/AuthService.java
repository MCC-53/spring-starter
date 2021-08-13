/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mii.kucoba.dto.AuthResponse;
import mii.kucoba.dto.LoginRequest;
import mii.kucoba.models.User;
import mii.kucoba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author haikal
 */
@Service
public class AuthService {
    private UserRepository userRepository;
    
    private UserDetailService userDetailService;
    
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, UserDetailService userDetailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }
    
    public AuthResponse prosesLogin(LoginRequest req) {
        AuthResponse authResponse = new AuthResponse();
        
        User user = new User();
        
        user = userRepository.findByUsername(req.getUsername());
        
        if (user == null) {
            throw new UsernameNotFoundException("Pengguna Ga ada GOBLOK");
        }
        
        boolean pass = passwordEncoder.matches(req.getPassword(), user.getPassword());
        
        if(pass == true) {
            List<String> data = userDetailService.loadUserByUsername(req.getUsername()).getAuthorities()
            .stream()
            .map(auth -> auth.getAuthority())
            .collect(Collectors.toList());                    
            authResponse.setAuthorities(data);
            
            return authResponse;
        }else {
            throw new RuntimeException("PASSWORD SALAH GOBLOKKK");
        }

    }
    
}
