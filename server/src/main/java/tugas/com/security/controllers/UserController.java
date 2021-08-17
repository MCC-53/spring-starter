package tugas.com.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tugas.com.security.services.AuthService;
import tugas.com.security.models.dto.RegisterDto;
import tugas.com.security.models.Employee;
import tugas.com.security.repositories.EmployeeRepository;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tugas.com.security.models.ResponseMessage;
import tugas.com.security.models.dto.AuthResponse;
import tugas.com.security.models.dto.LoginRequest;
import tugas.com.security.services.LoginService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthService registerService;

    @Autowired
    private LoginService loginService;
    @PostMapping("/register")
    public RegisterDto register(@RequestBody RegisterDto registerDto){
        return registerService.saveRegister(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest data){
        return new ResponseEntity
                (loginService.login(data), HttpStatus.OK);
    }
    @GetMapping("/findall")
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

}
