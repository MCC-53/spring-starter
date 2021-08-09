package com.nieceoftimes.serverside.auth.controller;

import com.nieceoftimes.serverside.auth.model.request.LoginRequest;
import com.nieceoftimes.serverside.auth.model.request.RegisterRequest;
import com.nieceoftimes.serverside.auth.model.response.LoginResponse;
import com.nieceoftimes.serverside.auth.service.impl.AuthServiceImpl;
import com.nieceoftimes.serverside.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterRequest registerRequest) {
        authService.registerUser(registerRequest);

        return new ResponseEntity<>(
                new ApiResponse("Successfully registered user"),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.loginUser(loginRequest);

        return new ResponseEntity<>(
                new ApiResponse("Successfully login user",
                        loginResponse),
                HttpStatus.OK);
    }
}
