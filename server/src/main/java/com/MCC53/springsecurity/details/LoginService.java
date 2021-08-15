/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.details;

import com.MCC53.springsecurity.dto.AuthResponse;
import com.MCC53.springsecurity.dto.LoginDto;
import com.MCC53.springsecurity.models.User;
import com.MCC53.springsecurity.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author user
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    public AuthResponse login(LoginDto loginDto) {

        AuthResponse authorisasi = new AuthResponse();
        
        User user = new User();
        
        user = userRepository.findByUsername(loginDto.getUsername());
        
        if (user == null ){
            throw new UsernameNotFoundException("Username tidak di temukan");
        }
        boolean password = passwordEncoder.matches(loginDto.getPassword(),user.getPassword());

        if (password == true){
            
           
            
            List <String> datarequest = appUserDetailsService.loadUserByUsername(loginDto.getUsername()).getAuthorities()
                    .stream()
                    .map(auth -> auth.getAuthority())
                    .collect(Collectors.toList());
//            
            
////            datarequest.add(user.getUsername());
////            datarequest.add(user.getPassword());
//            datarequest.add(authority);
            authorisasi.setAuthorities(datarequest);
            
            return authorisasi;
        }else{
            throw new UsernameNotFoundException("Password tidak ditemukan");
        }
    }
}