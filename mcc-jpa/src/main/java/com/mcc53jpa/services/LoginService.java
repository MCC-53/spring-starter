package com.mcc53jpa.services;

import com.mcc53jpa.models.User;
import com.mcc53jpa.models.request.AuthRequest;
import com.mcc53jpa.models.request.LoginRequest;
import com.mcc53jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

    private AppUserDetailService appUserDetailService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(AppUserDetailService appUserDetailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.appUserDetailService = appUserDetailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthRequest loginProcess(LoginRequest loginRequest){
        AuthRequest auth = new AuthRequest();
        User user = new User();
        user = userRepository.findByUsername(loginRequest.getUsername());

        if (user == null){
            throw new UsernameNotFoundException("Username Not Found");
        }
        Boolean pass = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (pass = true){
            String detailLogin = appUserDetailService.loadUserByUsername(loginRequest.getUsername()).getAuthorities().toString();
            List<String>data = new ArrayList<>();
            data.add(user.getUsername());
            data.add(user.getPassword());
            data.add(detailLogin);
            auth.setAuth(data);
            return auth;
        }else {
            throw new UsernameNotFoundException("Wrong Password");
        }
    }
}
