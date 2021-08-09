/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import java.util.List;
import mcc53.com.details.RegisterService;
import mcc53.com.dto.RegisterDto;
import mcc53.com.models.Employee;
import mcc53.com.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private RegisterService registerService;
    
    @PostMapping("/register")
    public RegisterDto register(@RequestBody RegisterDto registerDto){
        return registerService.saveRegister(registerDto);
    }
    
    @GetMapping("/findall")
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }
}