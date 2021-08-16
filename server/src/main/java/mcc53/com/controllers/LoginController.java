package mcc53.com.controllers;

import mcc53.com.models.ResponseMessage;
import mcc53.com.models.dto.LoginRequest;
import mcc53.com.models.dto.LoginResponse;
import mcc53.com.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<LoginResponse>> login(
            @RequestBody LoginRequest request) {
        System.out.println(request);
        ResponseMessage<LoginResponse> response = new ResponseMessage<>();
        response.getMessage().add("Login success!");
        response.setData(loginService.login(request));
        response.setStatus(true);
        return ResponseEntity.ok(response);
    }
}
