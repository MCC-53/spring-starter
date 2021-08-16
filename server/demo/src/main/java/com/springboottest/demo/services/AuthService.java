/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.AuthRequest;
import com.springboottest.demo.models.AuthResponse;
import com.springboottest.demo.models.Users;
import com.springboottest.demo.repositories.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
/**
 *
 * @author ACER
 */
@Service
public class AuthService {
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    private AppUserDetailService appUserDetailService;
    public AuthService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, AppUserDetailService appUserDetailService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailService = appUserDetailService;
    } public AuthResponse authUserLogin(AuthRequest authRequest) {
        AuthResponse authResponse = new AuthResponse();
        List<String> listAuthorities = new ArrayList<>();
        Users users = usersRepository.findByUsername(authRequest.getUsername());
        if (users == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username salah!");
        } boolean passwordCheck = passwordEncoder.matches(authRequest.getPassword(), users.getPassword());
        if (passwordCheck == false) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password salah!");
        } listAuthorities = appUserDetailService.loadUserByUsername(authRequest.getUsername()).getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
        authResponse.setAuthoritiesResponse(listAuthorities);
        System.out.println("Login Berhasil!");
        return authResponse;
    }
}
