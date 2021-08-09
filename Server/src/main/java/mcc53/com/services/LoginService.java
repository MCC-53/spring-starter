/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.services;

import java.util.ArrayList;
import java.util.List;
import mcc53.com.details.AppUserDetailService;
import mcc53.com.dto.LoginDto;
import mcc53.com.models.User;
import mcc53.com.models.request.Authorization;
import mcc53.com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xvitas
 */
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
    
    public Authorization prosesLogin(LoginDto l){
        
        Authorization authorization = new Authorization();
        
        User user = new User();
        
        user = userRepository.findByUsername(l.getUsername());
        
        if(user == null){
            throw new UsernameNotFoundException("Username tidak di temukan");
        }
        boolean pass = passwordEncoder.matches(l.getPassword(), user.getPassword());
        if(pass == true){
            String dat = appUserDetailService.loadUserByUsername(l.getUsername()).getAuthorities().toString();
            List<String> data = new ArrayList<>();
//                data.add(user.getUsername());
//                data.add(user.getPassword());
                data.add(dat);
                authorization.setAuth(data);
            return authorization;
        }else{
            throw new UsernameNotFoundException("password tidak di temukan");
        }
    }
}
