package com.mcc53jpa.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Long departmentId;
    private String username;
    private String password;
}
