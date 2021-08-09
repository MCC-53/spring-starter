/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.controllers;

import com.MCC53.springsecurity.models.Employee;
import com.MCC53.springsecurity.models.Project;
import com.MCC53.springsecurity.services.EmployeeService;
import com.MCC53.springsecurity.services.ProjectService;
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
 * @author user
 */
@RestController
@RequestMapping("/employee-project")
@PreAuthorize("hasAnyRole('ADMIN')")
public class EmployeeProject {
    
    private EmployeeService employeeService;
    private ProjectService projectService;

    @Autowired
    public EmployeeProject(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }
    @GetMapping("/project/{id}")
    public ResponseEntity<List<Employee>> getByProjectId ( 
    @PathVariable ("id") Long projectId){
        return new ResponseEntity(employeeService.findByProjectId(projectId), HttpStatus.OK);
    } 


    @GetMapping("/employee/{id}")
    public ResponseEntity<List<Project>> getByEmployeeId (
    @PathVariable ("id") Long employeeId){
        return new ResponseEntity(projectService.findByEmployeeId(employeeId), HttpStatus.OK);
    }
}
