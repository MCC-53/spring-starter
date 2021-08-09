/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.controllers;

import com.mii.coba.models.Department;
import com.mii.coba.services.DepartmentService;
import java.util.List;
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

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping
    public ResponseEntity<List<Department>> getAllDataDepartment(){
        return new ResponseEntity(departmentService.getAllDepartment(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Department> createDataDepartment(@RequestBody Department d){
        return new ResponseEntity<>(departmentService.createDepartment(d), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDataDepartment(@PathVariable("id") Integer id,
            @RequestBody Department department){
        return new ResponseEntity<>(departmentService.updateDepartment(id, department), HttpStatus.OK);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDataDepartment(@PathVariable("id") Integer id){
        return new ResponseEntity<>(departmentService.deleteDepartment(id), HttpStatus.OK);
    }
}
