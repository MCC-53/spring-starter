/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.controllers;

import com.MCC53.springsecurity.models.Department;
import com.MCC53.springsecurity.models.Employee;
import com.MCC53.springsecurity.services.DepartmentService;
import com.MCC53.springsecurity.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping ("/employee-department")
@PreAuthorize("hasAnyRole('ADMIN')")
public class EmployeeDepartment {
    
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeDepartment(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }
//yang di department 2, ada employee siapa aja?
    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> getByDepartmentId(
            @PathVariable("id") Long departmentId) {
        return new ResponseEntity(employeeService.findByDepartmentId(departmentId), HttpStatus.OK);
    }
//yg employee id =? kerja di department berapa
    @GetMapping("/employee/{id}")
    public ResponseEntity<Department> getByEmployeeId(@PathVariable("id") Long id) {
        return new ResponseEntity(departmentService.findByEmployeeId(id), HttpStatus.OK);
    }
    
}
