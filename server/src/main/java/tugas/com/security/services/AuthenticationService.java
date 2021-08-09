/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.context.Context;
import tugas.com.security.dto.LoginDto;
import tugas.com.security.dto.LoginSuccessDto;
import tugas.com.security.dto.RegisterDto;
import tugas.com.security.models.Department;
import tugas.com.security.models.EmailToSend;
import tugas.com.security.models.Employee;
import tugas.com.security.models.User;
import tugas.com.security.repositories.EmployeeRepository;
import tugas.com.security.repositories.RoleRepository;
import tugas.com.security.repositories.UserRepository;
import tugas.com.security.security.UserDetail;

/**
 *
 * @author putug
 */
@Service
public class AuthenticationService {
    private EmployeeRepository _employeeRepository;
    private UserRepository _userRepository;
    private RoleRepository _roleRepository;
    private PasswordEncoder _passwordEncoder;
    private EmailService _emailService;
    
    @Autowired
    public AuthenticationService(EmployeeRepository employeeRepository, 
            UserRepository userRepository, 
            RoleRepository roleRepository, 
            PasswordEncoder passwordEncoder,
            EmailService emailService)
    {
        _employeeRepository = employeeRepository;
        _userRepository = userRepository;
        _roleRepository = roleRepository;
        _passwordEncoder = passwordEncoder;
        _emailService = emailService;
    }
    
    public User registreationProcess(RegisterDto registerDto){
        User user = register(registerDto);
        EmailToSend emailToSend = new EmailToSend();
        emailToSend.setTo(registerDto.getEmail());
        emailToSend.setSubject("Register Success");
        emailToSend.setText("email/registerSuccess"); // Path html emailnya
        _emailService.sendSimpleMessage(emailToSend, emailRegisterContext(registerDto));
        return user;
    }
    
    private User register(RegisterDto registerDto){
        Employee employee = new Employee();
        employee.setFirstName(registerDto.getFirstName());
        employee.setLastName(registerDto.getLastName());
        employee.setAddress(registerDto.getAddress());
        employee.setEmail(registerDto.getEmail());
        employee.setDepartment(new Department(registerDto.getDepartment_id()));

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(_passwordEncoder.encode(registerDto.getPassword()));
        user.setEmployee(_employeeRepository.save(employee));
        _userRepository.save(user);
        
        return user;
    }
    
    private Context emailRegisterContext(RegisterDto registerDto){
        Context c = new Context();
        c.setVariable("model", registerDto);
               
        return c;
    }
    
    //login
    public LoginSuccessDto login(LoginDto loginDto){
        LoginSuccessDto loginSeccessDto = new LoginSuccessDto();
        
        UserDetail userData = new UserDetail(_userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> 
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Username tidak ditemukan")));
        
        boolean isMatched = _passwordEncoder
                .matches(loginDto.getPassword(), userData.getPassword());
        
        if(isMatched){
            loginSeccessDto.setUsername(userData.getUsername());
            List<String> permissions = new ArrayList();
            for (GrantedAuthority permission : userData.getAuthorities()) {
                permissions.add(permission.getAuthority());
            }
            loginSeccessDto.setPermissions(permissions);
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username & Password tidak cocok");
        }

        return loginSeccessDto;
    }
}
