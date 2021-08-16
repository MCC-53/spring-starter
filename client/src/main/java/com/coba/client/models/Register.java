package com.coba.client.models;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String username;
    private String password;
    private Long department_id;
}

