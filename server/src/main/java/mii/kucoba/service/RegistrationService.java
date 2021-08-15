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
import mii.kucoba.models.request.SendEmail;
import mii.kucoba.repository.EmployeeRepository;
import mii.kucoba.repository.UserRepository;
import mii.kucoba.service.admin.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

/**
 *
 * @author abiyo
 */
@Service
public class RegistrationService {
    
    private UserRepository userRepository;
    
    private PasswordEncoder passwordEncoder;
    
    private EmployeeRepository employeeRepository;
    
    private SendEmailService sendEmailService;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository, SendEmailService sendEmailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
        this.sendEmailService = sendEmailService;
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

        SendEmail sendEmail = new SendEmail();
        sendEmail.setTo(r.getEmail());
        sendEmail.setSubject("Selamat Anda Terdaftar");
        sendEmailService.sendSimpleMessage(sendEmail, registerContext(r));
        
        return r;
        
    }
    
    private Context registerContext(Registration registerDto) {
        Context context = new Context();
        context.setVariable("fullName", registerDto.getName()+" "+registerDto.getLastname());
        
        return context;
    }
    
    
    
}
