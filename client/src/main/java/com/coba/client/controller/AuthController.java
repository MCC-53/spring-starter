package com.coba.client.controller;

import com.coba.client.models.AuthResponse;
import com.coba.client.models.Login;
import com.coba.client.models.ResponseSingle;
import com.coba.client.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model){
        return "auth/login";
    }

    @PostMapping("/login")
    public @ResponseBody ResponseSingle<AuthResponse> postLogin(@RequestBody Login login){
        return loginService.loginRequest(login);
    }

    @GetMapping("/register")
    public String registerForm(){
        return "auth/register";
    }
}
