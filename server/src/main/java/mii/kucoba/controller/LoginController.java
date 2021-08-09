/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.controller;


import mii.kucoba.dto.AuthResponse;
import mii.kucoba.dto.LoginRequest;
import mii.kucoba.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author haikal
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private AuthService authService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping
    public ResponseEntity<AuthResponse> logSend(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity(authService.prosesLogin(loginRequest), HttpStatus.OK);
    }
    
    
}
