package mcc53.com.controllers;

import mcc53.com.models.AuthRequest;
import mcc53.com.models.AuthResponse;
import mcc53.com.models.Employee;
import mcc53.com.models.RegisterRequest;
import mcc53.com.repositories.EmployeeRepository;
import mcc53.com.services.AuthService;
import mcc53.com.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public RegisterRequest register(@RequestBody RegisterRequest registerRequest){
        return registerService.saveRegister(registerRequest);
    }

    @GetMapping("/findall")
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<AuthResponse> authUserLogin(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity(authService.authUserLogin(authRequest), HttpStatus.OK);
    }
}
