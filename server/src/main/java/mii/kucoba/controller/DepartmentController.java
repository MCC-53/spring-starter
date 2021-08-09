/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.controller;

import java.util.List;
import mii.kucoba.config.ResponseMessage;
import mii.kucoba.models.Department;
import mii.kucoba.service.DepartmentService;
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
 * @author haikal
 */
@RestController
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
@RequestMapping("/department")
public class DepartmentController {
    
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('READ_DEPARTMENT')")
    public ResponseEntity<List<Department>> getAllDataDepartment(){
        return new ResponseEntity(departmentService.getAllDepartment(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Integer id){
        return new ResponseEntity(departmentService.getDepartmentById(id), HttpStatus.OK);
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_DEPARTMENT')")
    public ResponseEntity<Department> createDataDepartment(@RequestBody Department d){
        return new ResponseEntity(departmentService.createDepartment(d), HttpStatus.OK);
    }
    
    @PutMapping("/{id}") 
    public ResponseEntity<Department> update(@PathVariable("id") Integer id,
            @RequestBody Department employee) {
        return new ResponseEntity(departmentService.update(id, employee), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Department> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity(new ResponseMessage<Department>(departmentService.delete(id), "Success"), HttpStatus.OK);
    }
}
