/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import mcc53.com.models.SendEmail;
import mcc53.com.dto.RegisterDto;
import mcc53.com.models.Department;
import mcc53.com.models.Employee;
import mcc53.com.models.User;
import mcc53.com.repositories.EmployeeRepository;
import mcc53.com.repositories.UserRepository;

/**
 *
 * @author Xvitas
 */
@Service
public class AuthService {
    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private SendEmailService sendEmailService;

    @Autowired
    public AuthService(EmployeeRepository employeeRepository, UserRepository userRepository,
                       PasswordEncoder passwordEncoder, SendEmailService sendEmailService) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sendEmailService = sendEmailService;
    }

    public RegisterDto saveRegister(RegisterDto registerDto) {
//        System.out.println(registerDto.toString());
        Employee employee = new Employee();
        employee.setFirstName(registerDto.getFirstName());
        employee.setLastName(registerDto.getLastName());
        employee.setAddress(registerDto.getAddress());
        employee.setEmail(registerDto.getEmail());
        employee.setDepartment(new Department(registerDto.getDepartment_id()));

        User user = new User();
        user.setUsername(registerDto.getUsername());
//        user.setPassword(registerDto.getPassword());
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEmployee(employeeRepository.save(employee));
        userRepository.save(user);

        SendEmail sendEmail = new SendEmail();
        sendEmail.setTo(registerDto.getEmail());
        sendEmail.setSubject("Selamat Anda Terdaftar");
        sendEmailService.sendSimpleMessage(sendEmail, registerContext(registerDto));

        return registerDto;
    }

    private Context registerContext(RegisterDto registerDto){
        Context context = new Context();
        context.setVariable("fullName", registerDto.getFirstName()+" "+registerDto.getLastName());
        return context;
    }



}