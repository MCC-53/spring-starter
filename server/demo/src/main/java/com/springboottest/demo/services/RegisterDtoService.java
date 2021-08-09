/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.Departments;
import com.springboottest.demo.models.Employees;
import com.springboottest.demo.models.RegisterDto;
import com.springboottest.demo.models.Users;
import com.springboottest.demo.repositories.EmployeesRepository;
import com.springboottest.demo.repositories.ProjectsRepository;
import com.springboottest.demo.repositories.RolesRepository;
import com.springboottest.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
/**
 *
 * @author ACER
 */
@Service
public class RegisterDtoService {
    private EmployeesRepository employeesRepository;
    private UsersRepository usersRepository;
    private EmployeesService employeesService;
    private ProjectsRepository projectsRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public RegisterDtoService(EmployeesRepository employeesRepository, UsersRepository usersRepository, PasswordEncoder passwordEncoder, EmployeesService employeesService, ProjectsRepository projectsRepository, RolesRepository rolesRepository) {
        this.employeesRepository = employeesRepository;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeesService = employeesService;
        this.projectsRepository = projectsRepository;
        this.rolesRepository = rolesRepository;
    } public RegisterDto createRegister(RegisterDto registerDto) {
        Employees employees = Employees.builder()
                .email(registerDto.getEmail())
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .phoneNumber(registerDto.getPhoneNumber())
                .departments(Departments.builder()
                        .id(registerDto.getDepartmentId())
                        .build())
                .projects(registerDto.getProjects())
                .build();
        Users users = Users.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialNonExpired(true)
                .isEnable(true)
                .employees(employeesRepository.save(employees))
                .roles(registerDto.getRoles())
                .build();
        usersRepository.save(users);
        return registerDto;
    } public RegisterDto update(Long id, RegisterDto registerDto) {
        Employees employeesTemp = employeesService.getById(id);
        Employees employees = Employees.builder()
                .id(employeesTemp.getId())
                .email(registerDto.getEmail())
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .phoneNumber(registerDto.getPhoneNumber())
                .departments(Departments.builder()
                        .id(registerDto.getDepartmentId())
                        .build())
                .projects(registerDto.getProjects())
                .build();
        Users users = Users.builder()
                .id(employeesTemp.getId())
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialNonExpired(true)
                .isEnable(true)
                .employees(employeesRepository.save(employees))
                .roles(registerDto.getRoles())
                .build();
        usersRepository.save(users);
        return registerDto;
    } public RegisterDto delete(Long id) {
        Employees employees = employeesService.getById(id);
        projectsRepository.deleteEmployeeId(employees.getId());
        rolesRepository.deleteUserId(employees.getId());
        usersRepository.deleteById(employees.getId());
        employeesService.delete(id);
        return new RegisterDto();
    }
}