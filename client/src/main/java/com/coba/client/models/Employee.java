package com.coba.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
        private Long id;
        private String address;
        private String firstName;
        private String lastName;
        private String email;
        private Department department;
        private List<Project> projects;
}
