/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mcc53.com.details.AppUserDetailService;
import mcc53.com.models.User;
import mcc53.com.dto.LoginDto;
import mcc53.com.models.request.Authorization;
import mcc53.com.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Authorization prosesLogin(LoginDto l) {
        Authorization authorization = new Authorization();
        User user = new User();
        user = userRepository.findByUsername(l.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username '%s' not found", l.getUsername()));
        }

        boolean pass = passwordEncoder.matches(l.getPassword(), user.getPassword());

        if (pass == true) {
            List<String> datarequest = appUserDetailService.loadUserByUsername(l.getUsername()).getAuthorities()
                    .stream()
                    .map(auth -> auth.getAuthority())
                    .collect(Collectors.toList());
            authorization.setAuth(datarequest);
            return authorization;
        } else {
            throw new UsernameNotFoundException("password tidak di temukan");
        }
    }
}
