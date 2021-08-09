package com.mcc53jpa.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {
    private String username;
    private String password;

    private List<GrantedAuthority> roles;
}
