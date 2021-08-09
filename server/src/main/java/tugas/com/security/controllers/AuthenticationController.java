/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tugas.com.security.dto.LoginDto;
import tugas.com.security.dto.LoginSuccessDto;
import tugas.com.security.dto.RegisterDto;
import tugas.com.security.models.User;
import tugas.com.security.services.AuthenticationService;

/**
 *
 * @author putug
 */

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    public AuthenticationService _authenticationService;
    
    public AuthenticationController(AuthenticationService authenticationService){
        _authenticationService = authenticationService;
    }
    
    //register
    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto){
        return new ResponseEntity(_authenticationService.Register(registerDto), HttpStatus.CREATED);
    }
    
    @GetMapping("register")
    public String getRegister(){
        return "Hello World";
    }
    
    //login
    @PostMapping("login")
    public ResponseEntity<LoginSuccessDto> login(@RequestBody LoginDto loginDto){
        return new ResponseEntity(_authenticationService.login(loginDto), HttpStatus.OK);
    }
}
