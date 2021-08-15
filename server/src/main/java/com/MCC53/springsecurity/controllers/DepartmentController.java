/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.controllers;

import com.MCC53.springsecurity.models.Department;
import com.MCC53.springsecurity.services.DepartmentService;
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
@RequestMapping("/department")
@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @PreAuthorize("hasAuthority ('READ_DATA')")
    public ResponseEntity<List<Department>> getAll() {
        return new ResponseEntity(departmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority ('READ_DATA')")
    public ResponseEntity<Department> getById(
            @PathVariable("id") Long id) {
        return new ResponseEntity(departmentService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Department> create(@RequestBody Department department) {

        return new ResponseEntity(departmentService.create(department), HttpStatus.OK);
    }

    @PutMapping("/{id}") //update data department by id
    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Department> update(@PathVariable("id") Long id,
            @RequestBody Department department) {

        return new ResponseEntity(departmentService.update(id, department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")//by id
    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Department> delete(@PathVariable("id") Long id) {

        return new ResponseEntity(departmentService.delete(id), HttpStatus.OK);
    }
}
