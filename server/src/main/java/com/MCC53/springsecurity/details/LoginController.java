/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.details;

import com.MCC53.springsecurity.dto.AuthResponse;
import com.MCC53.springsecurity.dto.LoginDto;
import com.MCC53.springsecurity.models.Employee;
import com.MCC53.springsecurity.models.ResponseMessage;
import com.MCC53.springsecurity.repositories.EmployeeRepository;
import java.util.List;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<AuthResponse> Login(@RequestBody LoginDto datalogin) {

        return new ResponseEntity(loginService.login(datalogin), HttpStatus.OK);
    }
    
    @GetMapping("/findAll")
    public List <Employee> findAll(){
        return employeeRepository.findAll();
    }

}
