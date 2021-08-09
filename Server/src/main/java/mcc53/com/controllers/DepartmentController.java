/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Department;
import mcc53.com.models.ResponseMessage;
import mcc53.com.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
 * @author Xvitas
 */
@RestController
@RequestMapping("/department")
//@PreAuthorize("hasAnyRole('admin', 'employee')")
public class DepartmentController {
    
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping
//    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Department>> getAll() {
        return new ResponseEntity(departmentService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Department> getById(
            @PathVariable("id") Integer id) {
        return new ResponseEntity(departmentService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Department> create(@RequestBody Department department) {
        return new ResponseEntity(departmentService.create(department), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Department> update(
            @PathVariable("id") Integer id, @RequestBody Department department) {
        return new ResponseEntity(departmentService.update(id, department), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Department> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity(departmentService.delete(id), HttpStatus.OK);
    }
}