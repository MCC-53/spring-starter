package tugas.com.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tugas.com.security.models.ResponseMessage;
import tugas.com.security.models.request.AuthRequest;
import tugas.com.security.models.request.AuthResponse;
import tugas.com.security.services.AuthService;
import tugas.com.security.models.request.RegisterDto;
import tugas.com.security.models.Employee;
import tugas.com.security.repositories.EmployeeRepository;
import tugas.com.security.services.LoginService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public RegisterDto register(@RequestBody RegisterDto registerDto){
        return authService.saveRegister(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest data){
        return new ResponseEntity(new ResponseMessage<AuthResponse>
                (loginService.login(data), "Succes"), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

}
