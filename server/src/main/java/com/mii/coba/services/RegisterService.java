/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.services;

import com.mii.coba.models.Department;
import com.mii.coba.models.User;
import com.mii.coba.models.request.RegisterDto;
import com.mii.coba.models.Employee;
import com.mii.coba.models.SendEmail;
import com.mii.coba.repositories.EmployeeRepository;
import com.mii.coba.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

/**
 *
 * @author HP
 */
@Service
public class RegisterService {

    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private SendEmailService sendEmailService;

    public RegisterService(UserRepository userRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, SendEmailService sendEmailService) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.sendEmailService = sendEmailService;
    }

    public RegisterDto saveRegister(RegisterDto registerDto) {

        Employee employee = new Employee();
        employee.setFirstName(registerDto.getFirstName());
        employee.setLastName(registerDto.getLastName());
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
        context.setVariable("fullName", registerDto.getFirstName() + " " + registerDto.getLastName());

        return context;
    }
}
