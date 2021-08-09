/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.controllers;
import com.springboottest.demo.models.Departments;
import com.springboottest.demo.models.Projects;
import com.springboottest.demo.services.EmployeesProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/employees-project")
public class EmployeesProjectController {
    private EmployeesProjectService employeesProjectService;
    @Autowired
    public EmployeesProjectController(EmployeesProjectService employeesProjectService) {
        this.employeesProjectService = employeesProjectService;
    }
    @GetMapping("/project/{id}")
    //@PreAuthorize("hasAuthority('READ_BY_PROJECT_ID')")
    public ResponseEntity<List<Projects>> getByProjectsId(@PathVariable("id") Long id) {
        return new ResponseEntity(employeesProjectService.findByProjectsId(id), HttpStatus.OK);
    }
    @GetMapping("/employee/{id}")
    //@PreAuthorize("hasAuthority('READ_BY_EMPLOYEE_ID')")
    public ResponseEntity<Departments> getByEmployeesId(@PathVariable("id") Long id) {
        return new ResponseEntity(employeesProjectService.findByEmployeesId(id), HttpStatus.OK);
    }
}