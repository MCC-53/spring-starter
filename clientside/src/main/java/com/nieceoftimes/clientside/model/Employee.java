package com.nieceoftimes.clientside.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private User user;
    private Department department;
    private List<Project> projects;
}
