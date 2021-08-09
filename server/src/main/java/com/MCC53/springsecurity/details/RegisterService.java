/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.details;

import com.MCC53.springsecurity.dto.RegisterDto;
import com.MCC53.springsecurity.models.Department;
import com.MCC53.springsecurity.models.Employee;
import com.MCC53.springsecurity.models.User;
import com.MCC53.springsecurity.repositories.EmployeeRepository;
import com.MCC53.springsecurity.repositories.RoleRepository;
import com.MCC53.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class RegisterService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder paswordEncoder;
    
    public RegisterDto SaveRegister (RegisterDto registerDto){
        
        Employee employee = new Employee();
        employee.setFirstName(registerDto.getFirstname());
        employee.setLastName(registerDto.getLastname());
        employee.setAddress(registerDto.getAddres());
        employee.setEmail(registerDto.getEmail());
        employee.setDepartment(new Department(registerDto.getDepartment_id()));
        
        User user = new User ();
        user.setUsername(registerDto.getUsername());
        String encodPassword = paswordEncoder.encode(registerDto.getPasword());
        user.setPassword(encodPassword);
        
        user.setEmployee(employeeRepository.save(employee));
        userRepository.save(user);
        
        return registerDto;
        
    }
    
 
}
