package com.nieceoftimes.serverside.auth.service.impl;

import com.nieceoftimes.serverside.auth.model.request.LoginRequest;
import com.nieceoftimes.serverside.auth.model.request.RegisterRequest;
import com.nieceoftimes.serverside.auth.model.response.LoginResponse;
import com.nieceoftimes.serverside.auth.service.AuthService;
import com.nieceoftimes.serverside.model.entity.Department;
import com.nieceoftimes.serverside.model.entity.Employee;
import com.nieceoftimes.serverside.model.entity.Role;
import com.nieceoftimes.serverside.model.entity.User;
import com.nieceoftimes.serverside.repository.DepartmentRepository;
import com.nieceoftimes.serverside.repository.EmployeeRepository;
import com.nieceoftimes.serverside.repository.RoleRepository;
import com.nieceoftimes.serverside.repository.UserRepository;
import com.nieceoftimes.serverside.util.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.nieceoftimes.serverside.enums.RoleName.ROLE_ADMIN;
import static com.nieceoftimes.serverside.enums.RoleName.ROLE_USER;

@Service
public class AuthServiceImpl implements AuthService {
    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private DepartmentRepository departmentRepository;
    private PasswordEncoder passwordEncoder;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AuthServiceImpl(EmployeeRepository employeeRepository,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           DepartmentRepository departmentRepository,
                           PasswordEncoder passwordEncoder,
                           UserDetailsServiceImpl userDetailsService
                           ) {
        this.employeeRepository =  employeeRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {

        Set<Role> roleSet = new HashSet<>();
        String[] roleList = registerRequest.getRole();

        if (roleList == null) {
            roleSet.add(roleRepository.findRoleByName(ROLE_USER)
                    .orElseThrow(() -> new ApiException("Role not found!", HttpStatus.NOT_FOUND)));
        } else {
            for (String role : roleList) {
                if (role.equalsIgnoreCase("admin")) {
                    roleSet.add(roleRepository.findRoleByName(ROLE_ADMIN)
                            .orElseThrow(() -> new ApiException("Role not found!", HttpStatus.NOT_FOUND)));
                } else if (role.equalsIgnoreCase("user")) {
                    roleSet.add(roleRepository.findRoleByName(ROLE_USER)
                            .orElseThrow(() -> new ApiException("Role not found!", HttpStatus.NOT_FOUND)));
                }
            }
        }

        Department departmentRetrieve = departmentRepository.findById(registerRequest.getDepartmentId())
                .orElseThrow(() -> new ApiException("Department not found", HttpStatus.NOT_FOUND));

        Employee employee = Employee
                .builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .address(registerRequest.getAddress())
                .department(departmentRetrieve)
                .build();

        User user = User
                .builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .employee(employeeRepository.save(employee))
                .roles(roleSet)
                .build();
        userRepository.save(user);

    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> authorities = new ArrayList<>();

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            authorities.add(userDetailsService.loadUserByUsername(loginRequest.getUsername()).getAuthorities().toString());
        } else {
            throw new ApiException("Failed Authentication!", HttpStatus.UNAUTHORIZED);
        }

        return LoginResponse
                .builder()
                .authorities(authorities)
                .build();
    }
}
