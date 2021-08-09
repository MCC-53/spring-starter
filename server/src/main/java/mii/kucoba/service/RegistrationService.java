/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.service;

import mii.kucoba.models.Department;
import mii.kucoba.models.Employee;
import mii.kucoba.models.User;
import mii.kucoba.models.request.Registration;
import mii.kucoba.repository.EmployeeRepository;
import mii.kucoba.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author abiyo
 */
@Service
public class RegistrationService {
    
    private UserRepository userRepository;
    
    private PasswordEncoder passwordEncoder;
    
    private EmployeeRepository employeeRepository;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }

    public Registration CreateRegistration(Registration r){
        System.out.println(passwordEncoder.encode("data"));
        Employee employee = new Employee();
        employee.setName(r.getName());
        employee.setLast_name(r.getLastname());
        employee.setAddress(r.getAddress());
        employee.setEmail(r.getEmail());
        employee.setDepartment(new Department(r.getDepartment_id()));

        User user = new User();
        user.setUsername(r.getUsername());
        user.setPassword(passwordEncoder.encode(r.getPassword()));
        user.setEmployee(employeeRepository.save(employee));
        userRepository.save(user);

        return r;
        
        
    }
    
}
