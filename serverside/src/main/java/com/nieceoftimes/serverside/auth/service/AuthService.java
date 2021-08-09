package com.nieceoftimes.serverside.auth.service;

import com.nieceoftimes.serverside.auth.model.request.LoginRequest;
import com.nieceoftimes.serverside.auth.model.request.RegisterRequest;
import com.nieceoftimes.serverside.auth.model.response.LoginResponse;

public interface AuthService {
    LoginResponse loginUser(LoginRequest loginRequest);
    void registerUser(RegisterRequest registerRequest);
}
