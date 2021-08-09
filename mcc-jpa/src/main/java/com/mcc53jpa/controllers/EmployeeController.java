package com.mcc53jpa.controllers;

import com.mcc53jpa.models.Employee;
import com.mcc53jpa.models.RespondMessage;
import com.mcc53jpa.repositories.EmployeeRepository;
import com.mcc53jpa.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//controller untuk RESTAPI
@RestController

/*
    Request method ada banyak;
    -get
    -delete
    -put
    -post
 */

@RequestMapping("/employee")
//@PreAuthorize("hasAnyRole('ADMIN', 'HR')")
public class EmployeeController {
    //pathVariable
    //localhost:8080/employee/{id}
    //HTTP Statuc Code

    /*
        getAll -> localhost:8080/employee -> GET
        getById -> localhost:8080/employee/{id} -> GET
        create -> localhost:8080/employee -> POST
        upadate -> localhost:8080/employee/{id} -> PUT
        delete -> localhost:8080/employee/{id} -> DELETE
    */

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
//    @PreAuthorize("hasAuthority('READ_EMPLOYEE')")
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // -> req method get: menampilkan data data
//    @PreAuthorize("hasAuthority('READ_EMPLOYEE')")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(employeeService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/where/{name}")
    public  ResponseEntity<List<Employee>> getEmployeeWithName(@PathVariable("name") String name){
        return new ResponseEntity<>(employeeService.findEmployeeWithName(name), HttpStatus.OK);
    }

    @PostMapping // -> req method post: create/insert data
//    @PreAuthorize("hasAuthority('CREATE_EMPLOYEE')")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        return new ResponseEntity(new RespondMessage<Employee>(employeeService.create(employee), "Success Created"), HttpStatus.OK);
    }

    @PutMapping("/{id}") // -> req method put: update data
//    @PreAuthorize("hasAuthority('UPDATE_EMPLOYEE')")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employee){
       return new ResponseEntity(employeeService.update(id, employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // -> req method delete: menghapus datas
//    @PreAuthorize("hasAuthority('DELETE_EMPLOYEE')")
    public ResponseEntity<Employee> delete(@PathVariable("id") Long id){
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }


}
