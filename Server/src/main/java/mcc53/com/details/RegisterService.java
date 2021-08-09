/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.details;

import mcc53.com.dto.RegisterDto;
import mcc53.com.models.Department;
import mcc53.com.models.Employee;
import mcc53.com.models.User;
import mcc53.com.repositories.EmployeeRepository;
import mcc53.com.repositories.RoleRepository;
import mcc53.com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xvitas
 */
@Service
public class RegisterService {
    private EmployeeRepository employeeRepository;
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    
    @Autowired
    public RegisterService(EmployeeRepository employeeRepository,
            UserRepository userRepository, 
            RoleRepository roleRepository){
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    
    public RegisterDto saveRegister (RegisterDto registerDto){
//        System.out.println(registerDto.toString());
        Employee employee = new Employee();
        employee.setFirstName(registerDto.getFirstName());
        employee.setLastName(registerDto.getLastName());
        employee.setAddress(registerDto.getAddress());
        employee.setEmail(registerDto.getEmail());
        employee.setDepartment(new Department(registerDto.getDepartment_id()));
        
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setEmployee(employeeRepository.save(employee));
        userRepository.save(user);
        
        return registerDto;
    }
}