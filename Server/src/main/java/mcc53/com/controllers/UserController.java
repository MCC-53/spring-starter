/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mcc53.com.models.request.Authorization;
import mcc53.com.dto.LoginDto;
import mcc53.com.services.AuthService;
import mcc53.com.dto.RegisterDto;
import mcc53.com.models.Employee;
import mcc53.com.repositories.EmployeeRepository;
import mcc53.com.services.LoginService;

import java.util.List;

/**
 *
 * @author Xvitas
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public RegisterDto register(@RequestBody RegisterDto registerDto) {
        return authService.saveRegister(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Authorization> login(@RequestBody LoginDto data) {
        return new ResponseEntity(loginService.prosesLogin(data), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
