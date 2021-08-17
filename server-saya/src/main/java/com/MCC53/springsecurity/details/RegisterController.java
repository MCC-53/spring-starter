/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.details;

import com.MCC53.springsecurity.dto.RegisterDto;
import com.MCC53.springsecurity.models.Employee;
import com.MCC53.springsecurity.repositories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/register")
public class RegisterController {
 
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private RegisterService registerService;
    
    
    @PostMapping
    public RegisterDto register (@RequestBody RegisterDto registerDto){
        return registerService.SaveRegister(registerDto);
    }
    
    @GetMapping("/findAll")
    public List <Employee> findAllEmp(){
        return employeeRepository.findAll();
    }
}
