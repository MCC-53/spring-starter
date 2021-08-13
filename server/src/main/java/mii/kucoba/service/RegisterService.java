/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.service;

import mii.kucoba.dto.RegisterDto;
import mii.kucoba.models.Department;
import mii.kucoba.models.Employee;
import mii.kucoba.models.SendEmail;
import mii.kucoba.models.User;
import mii.kucoba.repository.EmployeeRepository;
import mii.kucoba.repository.admin.RoleRepository;
import mii.kucoba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

/**
 *
 * @author haikal
 */
@Service
public class RegisterService {
    private EmployeeRepository employeeRepository;
    
    private UserRepository userRepository;
    
    private RoleRepository roleRepository;
    
    private PasswordEncoder passwordEncoder;
    
    private SendEmailService sendEmailService;
    
    @Autowired
    public RegisterService(EmployeeRepository employeeRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, SendEmailService sendEmailService) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.sendEmailService = sendEmailService;
    }
    
    public RegisterDto saveRegister(RegisterDto registerDto) {
        
//        System.out.println(registerDto.toString());
        Employee employee = new Employee();
        employee.setName(registerDto.getName());
        employee.setLast_name(registerDto.getLast_name());
        employee.setAddress(registerDto.getAddress());
        employee.setEmail(registerDto.getEmail());
        employee.setDepartment(new Department(registerDto.getDepartment_id()));

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmployee(employeeRepository.save(employee));
        userRepository.save(user);
        
        SendEmail sendEmail = new SendEmail();
        sendEmail.setTo(registerDto.getEmail());
        sendEmail.setSubject("Selamat Anda Terdaftar");
        sendEmailService.sendSimpleMessage(sendEmail, registerContext(registerDto));
        

        return registerDto;
    }
    
    private Context registerContext(RegisterDto registerDto) {
        Context context = new Context();
        context.setVariable("fullName", registerDto.getName()+" "+registerDto.getLast_name());
        
        return context;
    }
}
