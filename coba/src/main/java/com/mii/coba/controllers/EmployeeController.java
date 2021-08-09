/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.controllers;

import com.mii.coba.models.Employee;
import com.mii.coba.services.EmployeeService;
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
 * @author HP
 */
@RestController
@RequestMapping("/employee")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//@PreAuthorize("hasAuthority('CREATE')")
public class EmployeeController {
    
    private EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity(employeeService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }
            
    @PostMapping
    public ResponseEntity<Employee> createDataEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.insertDataEmployee(employee), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Integer id,
            @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.update(id, employee), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable("id") Integer id){
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }
    
}