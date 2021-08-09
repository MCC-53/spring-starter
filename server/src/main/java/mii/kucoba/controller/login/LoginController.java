package mii.kucoba.controller.login;

import mii.kucoba.Detail.AppUserDetail;
import mii.kucoba.config.ResponseMassage;
import mii.kucoba.models.Employee;
import mii.kucoba.models.request.Authorization;
import mii.kucoba.models.request.Login;
import mii.kucoba.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abiyo
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
    public ResponseEntity<Authorization> Login(@RequestBody Login data){
        return new ResponseEntity(new ResponseMassage<Authorization>
            (loginService.prosesLogin(data), "Success"), HttpStatus.OK);
    }
    
}
