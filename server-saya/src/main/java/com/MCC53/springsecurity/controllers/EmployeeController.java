/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.controllers;

import com.MCC53.springsecurity.models.Employee;
import com.MCC53.springsecurity.models.ResponseMessage;
import com.MCC53.springsecurity.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping ("/employee")
//@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
public class EmployeeController {
    
    
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    
    @GetMapping 
//    @PreAuthorize("hasAuthority ('READ_DATA')")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity(employeeService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping ("/{id}")
//    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Employee> getById( @PathVariable ("id") Long id){
        return new ResponseEntity(employeeService.getById(id), HttpStatus.OK);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity(new ResponseMessage<Employee>
                (employeeService.create(employee), "employee created"), HttpStatus.OK);
    }
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id,
            @RequestBody Employee employee) {
        
        return new ResponseEntity(employeeService.update(id, employee), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Employee> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }
}
