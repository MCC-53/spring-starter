/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.controller;

import java.util.List;
import javax.xml.ws.Response;
import mii.kucoba.dto.AuthResponse;
import mii.kucoba.dto.LoginRequest;
import mii.kucoba.dto.RegisterDto;
import mii.kucoba.models.Employee;
import mii.kucoba.repository.EmployeeRepository;
import mii.kucoba.service.AuthService;
import mii.kucoba.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author haikal
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RegisterService registerService;
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public RegisterDto register(@RequestBody RegisterDto registerDto){
        return registerService.saveRegister(registerDto);
    }

    @GetMapping("/findall")
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }
}
