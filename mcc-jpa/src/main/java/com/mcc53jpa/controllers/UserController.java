package com.mcc53jpa.controllers;

import com.mcc53jpa.models.Employee;
import com.mcc53jpa.models.RespondMessage;
import com.mcc53jpa.models.request.AuthRequest;
import com.mcc53jpa.models.request.LoginRequest;
import com.mcc53jpa.models.request.RegisterRequest;
import com.mcc53jpa.repositories.EmployeeRepository;
import com.mcc53jpa.repositories.RegisterRepository;
import com.mcc53jpa.services.EmployeeService;
import com.mcc53jpa.services.LoginService;
import com.mcc53jpa.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//@PreAuthorize("ADMIN")
public class UserController {

    //Register Controller
        @Autowired
        EmployeeRepository employeeRepository;

        @Autowired
        RegisterService registerService;

        @PostMapping("/register")
        public RegisterRequest register(@RequestBody RegisterRequest registerRequest){
            return registerService.saveRegister(registerRequest);
        }

        @GetMapping()
        public ResponseEntity<List<Employee>> getAll(){
            return new ResponseEntity(employeeRepository.findAll(), HttpStatus.OK);
        }


    // Login Controller
        @Autowired
        LoginService loginService;

        @PostMapping("/login")
        public ResponseEntity<AuthRequest> loginProcess(@RequestBody LoginRequest loginRequest){
            return new ResponseEntity(new RespondMessage<AuthRequest>(
                    loginService.loginProcess(loginRequest),"Login Success"), HttpStatus.OK);
        }




}
