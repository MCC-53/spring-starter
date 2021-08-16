/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.frontend.controllers;

import com.mcc.frontend.models.AuthResponse;
import com.mcc.frontend.models.Login;
import com.mcc.frontend.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HP
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    
    @GetMapping
    public String indexLogin() {

        return "/login/index";
    }
    
    @PostMapping
    public @ResponseBody AuthResponse postLogin(@RequestBody Login login) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return loginService.loginRequest(login);
    }
}
