package com.mcc53jpa.controllers;


import com.mcc53jpa.models.Department;
import com.mcc53jpa.models.Employee;
import com.mcc53jpa.models.RespondMessage;
import com.mcc53jpa.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
//@PreAuthorize("hasAnyRole('ADMIN', 'HR')")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping
//    @PreAuthorize("hasAuthority('READ_DEPARTMENT')")
    public ResponseEntity<List<Department>> getAll(){
        return new ResponseEntity(departmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // -> req method get: menampilkan data data
    public ResponseEntity<Department> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(departmentService.getById(id), HttpStatus.OK);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE_DEPARTMENT')")
    public ResponseEntity<Department> create(@RequestBody Department department){
        return new ResponseEntity(departmentService.create(department), HttpStatus.OK);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('UPDATE_DEPARTMENT')")
    public ResponseEntity<Department> update(@PathVariable("id") Long id, @RequestBody Department department){
        return new ResponseEntity(departmentService.update(id, department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE_DEPARTMENT')")
    public ResponseEntity<Department> delete(@PathVariable("id") Long id){
        return new ResponseEntity(departmentService.delete(id), HttpStatus.OK);
    }

}
