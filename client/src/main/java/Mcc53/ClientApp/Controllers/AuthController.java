/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mcc53.ClientApp.Controllers;

import Mcc53.ClientApp.Models.User;
import Mcc53.ClientApp.Services.AuthService;
import Mcc53.ClientApp.ViewModels.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author putug
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    private AuthService _authService;
    
    @Autowired
    public AuthController(AuthService authService){
        _authService = authService;
    }
    
    @GetMapping("login")
    public String login(){
        return "loginPage";
    }
    
    @PostMapping("login")
    public @ResponseBody User login(@RequestBody LoginForm loginForm){
        return _authService.login(loginForm);
    }
}
