package com.nieceoftimes.serverside.auth.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {
    private List<String> authorities;
}
