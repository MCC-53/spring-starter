package mcc3.com.security.controllers;

import mcc3.com.security.models.request.AuthResponse;
import mcc3.com.security.models.request.LoginRequest;
import mcc3.com.security.models.request.RegisterData;
import mcc3.com.security.services.AuthService;
import mcc3.com.security.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author firmanzega
 */
@RestController
@RequestMapping("/users")
public class RegisterController {
    
    @Autowired
    private RegisterService registerService;
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public RegisterData register(@RequestBody RegisterData registerData){
        
        return registerService.register(registerData);
    }
    
    @GetMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
