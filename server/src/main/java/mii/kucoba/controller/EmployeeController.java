/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.controller;

import java.util.List;
import mii.kucoba.config.ResponseMassage;
import mii.kucoba.models.Employee;
import mii.kucoba.service.access.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author abiyo
 */

//localhost:port/emp

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    
    private EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
    public ResponseEntity<List<Employee>> getAllDataEmployee(){
        return new ResponseEntity(employeeService.getAllEmployee(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getDataEmployeeById(@PathVariable("id") Integer id){
        return new ResponseEntity(employeeService.getById(id), HttpStatus.OK);
    }
    
//    @GetMapping("/{name}")
//    public ResponseEntity<List<Employee>> getDataEmployeeByName(@PathVariable("name") String name){
//        return new ResponseEntity(employeeService.getEmployeeByName(name), HttpStatus.OK);
//    }
            
    @PostMapping
    public ResponseEntity<Employee> createDataEmployee(@RequestBody Employee employee){
        return new ResponseEntity(new ResponseMassage<Employee>
            (employeeService.insertDataEmployee(employee), "Success"), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Integer id,
            @RequestBody Employee employee) {
        return new ResponseEntity(employeeService.update(id, employee), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }
    
    @GetMapping("/dep/{id}")
    public ResponseEntity<Employee> getEmployeeByDepartmentId(@PathVariable("id") Integer dep){
        return new ResponseEntity(employeeService.getEmployeeByDepartment(dep), HttpStatus.OK);
    }
    
}

