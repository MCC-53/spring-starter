/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.controllers;

import com.mii.coba.config.ResponseMessage;
import com.mii.coba.models.request.AuthResponse;
import com.mii.coba.models.request.LoginRequest;
import com.mii.coba.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    
    @PostMapping
    public ResponseEntity<AuthResponse>Login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity(new ResponseMessage<AuthResponse>
        (loginService.prosesLogin(loginRequest), "Success"), HttpStatus.OK);
    }
}
