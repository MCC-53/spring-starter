package com.mii.clinetapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Department department;
}
