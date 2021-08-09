/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.AuthRequest;
import com.springboottest.demo.models.AuthResponse;
import com.springboottest.demo.models.Privileges;
import com.springboottest.demo.models.Users;
import com.springboottest.demo.repositories.UsersRepository;
import java.util.ArrayList;
import java.util.List;
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
    public AuthService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    } public AuthResponse authUserLogin(AuthRequest authRequest) {
        AuthResponse authResponse = new AuthResponse();
        List<String> listPrivileges = new ArrayList<>();
        Users users = usersRepository.findByUsername(authRequest.getUsername());
        if (users == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username salah!");
        } boolean passwordCheck = passwordEncoder.matches(authRequest.getPassword(), users.getPassword());
        if (passwordCheck == false) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password salah!");
        } for (Privileges privileges : users.getRoles().get(0).getPrivileges()) {
            listPrivileges.add(privileges.getName());
        } authResponse.setAuthoritiesResponse(listPrivileges);
        return authResponse;
    }
}