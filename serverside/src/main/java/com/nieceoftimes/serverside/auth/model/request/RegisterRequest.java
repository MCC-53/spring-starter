package com.nieceoftimes.serverside.auth.model.request;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String username;
    private String password;
    private String departmentId;
    private String[] role;
}
