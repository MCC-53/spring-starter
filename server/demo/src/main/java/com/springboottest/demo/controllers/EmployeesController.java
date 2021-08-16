/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.controllers;
import java.util.List;
import com.springboottest.demo.models.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboottest.demo.services.EmployeesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private EmployeesService employeesService;
    @Autowired
    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }
    @GetMapping
    @PreAuthorize("hasAuthority('READ_ALL_EMPLOYEES')")
    public ResponseEntity<List<Employees>> getAll() {
        return new ResponseEntity(employeesService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_EMPLOYEE_BY_ID')")
    public ResponseEntity<Employees> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(employeesService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/get-employee-by-username")
    @PreAuthorize("hasAuthority('READ_EMPLOYEE_BY_USERNAME')")
    public ResponseEntity<Employees> getById(Authentication authentication) {
        return new ResponseEntity(employeesService.getByEmail(authentication.getName()), HttpStatus.OK);
    }
    @GetMapping("/get-first-name/{first-name}")
    @PreAuthorize("hasAuthority('READ_EMPLOYEE_BY_FIRST_NAME')")
    public ResponseEntity<List<Employees>> getByEmployeesFirstName(@PathVariable("first-name") String firstName) {
        return new ResponseEntity(employeesService.getByEmployeesFirstName(firstName), HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_EMPLOYEE')")
    public ResponseEntity<Employees> create(@RequestBody Employees employees) {
        return new ResponseEntity(employeesService.create(employees), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_EMPLOYEE')")
    public ResponseEntity<Employees> update(@PathVariable("id") Long id, @RequestBody Employees employees) {
        return new ResponseEntity(employeesService.update(id, employees), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_EMPLOYEE')")
    public ResponseEntity<Employees> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(employeesService.delete(id), HttpStatus.OK);
    }
}