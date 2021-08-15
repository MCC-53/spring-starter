    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Employee;
import mcc53.com.models.ResponseMessage;
import mcc53.com.models.request.ResponseEmployee;
import mcc53.com.services.EmployeeService;
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
 * @author Xvitas
 */
@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('admin')")
public class EmployeeController {
        
    /*
        getAll -> localhost:8080/employee -> GET
        getById -> localhost:8080/employee/{id} -> GET
        create -> localhost:8080/employee -> POST
        upadate -> localhost:8080/employee/{id} -> PUT
        delete -> localhost:8080/employee/{id} -> DELETE
    */    
    
    private EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    
    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity(employeeService.getAlll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ResponseEmployee> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity(employeeService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity(new ResponseMessage<Employee>
            (employeeService.create(employee), "employee created"), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Employee> update(@PathVariable("id") Integer id,
            @RequestBody Employee employee) {
        return new ResponseEntity(employeeService.update(id, employee), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Employee> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }
}