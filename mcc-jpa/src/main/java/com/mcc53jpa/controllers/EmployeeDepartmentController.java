package com.mcc53jpa.controllers;


import com.mcc53jpa.models.Department;
import com.mcc53jpa.models.Employee;
import com.mcc53jpa.services.DepartmentService;
import com.mcc53jpa.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee-department")
public class EmployeeDepartmentController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeDepartmentController(EmployeeService employeeService, DepartmentService departmentService){
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> getByDepartmentId(@PathVariable("id") Long id){
        return new ResponseEntity(employeeService.findByDepartmentId(id), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Department> getByEmployeeId(@PathVariable("id") Long id){
        return new ResponseEntity(departmentService.findByEmployeeId(id), HttpStatus.OK);
    }

}
