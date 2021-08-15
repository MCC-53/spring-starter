/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.client.clientapp.controllers;

import com.MCC53.client.clientapp.models.AuthResponse;
import com.MCC53.client.clientapp.models.Login;
import com.MCC53.client.clientapp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
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

//        model.addAttribute("login", new Login());
        
        return "/login/login";
    }

//    @PostMapping()
//    public @ResponseBody String postLogin(@RequestBody Login login) {
//
//        return "SUKSES BRO";
//
//    }
    @PostMapping
    public @ResponseBody AuthResponse postLogin(@RequestBody Login login) {
        System.out.println(login);
        
        
        return loginService.loginRequest(login);

    }
}
