/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.controllers;
import com.springboottest.demo.models.Departments;
import com.springboottest.demo.services.DepartmentsService;
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
 * @author ACER
 */
@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private DepartmentsService departmentsService;
    @Autowired
    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }
    @GetMapping
    //@PreAuthorize("hasAuthority('READ_ALL_DEPARTMENTS')")
    public ResponseEntity<List<Departments>> getAll() {
        return new ResponseEntity(departmentsService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('READ_DEPARTMENT_BY_ID')")
    public ResponseEntity<Departments> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(departmentsService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE_DEPARTMENT')")
    public ResponseEntity<Departments> create(@RequestBody Departments departments) {
        return new ResponseEntity(departmentsService.create(departments), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('UPDATE_DEPARTMENT')")
    public ResponseEntity<Departments> update(@PathVariable("id") Long id, @RequestBody Departments departments) {
        return new ResponseEntity(departmentsService.update(id, departments), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('DELETE_DEPARTMENT')")
    public ResponseEntity<Departments> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(departmentsService.delete(id), HttpStatus.OK);
    }
    /*@PostMapping
    public ResponseEntity<EmployeeDepartment> createEmployeeDepartment(EmployeeDepartment employeeDepartment) {
        return new ResponseEntity(departmentsService.createEmployeeDepartment(employeeDepartment), HttpStatus.OK);
    }*/
}