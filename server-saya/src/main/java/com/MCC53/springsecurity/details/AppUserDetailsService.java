/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.details;

import com.MCC53.springsecurity.dto.RegisterDto;
import com.MCC53.springsecurity.models.User;
import com.MCC53.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        
        //find username di database 
        User user = userRepository.findByUsername(username);
        if (user == null ) {
            throw new UsernameNotFoundException(" User Not Found ");
        }

        return new AppUserDetails(user);
        
    }
    
    //function register
//    public AppUserDetails Register (AppUserDetails appUserDetails){
//        
//        boolean userExist = userRepository.findByUsername(user.getUsername).
//
//      
//    }
}
