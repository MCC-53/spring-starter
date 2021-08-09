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
public class Department implements Serializable {
    private Long id;
    private String name;
    private List<Employee> employee;

}
