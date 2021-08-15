/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.front.controllers.login;

import mii.kucoba.front.models.request.Login;
import mii.kucoba.front.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author abiyo
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
    public String indexLogin(){
        return "layout/login";
    }
    
    @PostMapping
    public @ResponseBody String loginProcess(@RequestBody Login login){
//        System.out.println();
        return loginService.loginProcessService(login);
    }
    
}
