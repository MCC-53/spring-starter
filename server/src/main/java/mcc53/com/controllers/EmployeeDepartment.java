/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Department;
import mcc53.com.models.Employee;
import mcc53.com.services.DepartmentService;
import mcc53.com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WahyuKu
 */
@RestController
@RequestMapping("/employee-department")
public class EmployeeDepartment {
    
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    
    @Autowired
    public EmployeeDepartment(EmployeeService employeeService, 
            DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }
    
    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> getByDepartmentId(
            @PathVariable("id") Long departmentId) {
        return new ResponseEntity(employeeService.findByDepartmentId(departmentId), HttpStatus.OK);
    }
    
    @GetMapping("/employee/{id}")
    public ResponseEntity<Department> getByEmployeeId(
            @PathVariable("id") Long id) {
        return new ResponseEntity(departmentService.findByEmployeeId(id), HttpStatus.OK);
    }
}
