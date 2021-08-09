package com.mcc53jpa.services;

import com.mcc53jpa.models.Department;
import com.mcc53jpa.models.Employee;
import com.mcc53jpa.models.User;
import com.mcc53jpa.models.request.RegisterRequest;
import com.mcc53jpa.repositories.EmployeeRepository;
import com.mcc53jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    EmployeeRepository employeeRepository;
    UserRepository userRepository;

    @Autowired
    public RegisterService(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    public RegisterRequest saveRegister(RegisterRequest registerRequest){
        Employee employee = new Employee();
        employee.setFirstName(registerRequest.getFirstName());
        employee.setLastName(registerRequest.getLastName());
        employee.setEmail(registerRequest.getEmail());
        employee.setAddress(registerRequest.getAddress());
        employee.setDepartment(new Department(registerRequest.getDepartmentId()));

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmployee(employeeRepository.save(employee));
        userRepository.save(user);

        return registerRequest;
    }
}
