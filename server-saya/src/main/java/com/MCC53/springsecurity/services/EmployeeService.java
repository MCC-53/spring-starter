/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.services;

import com.MCC53.springsecurity.models.Employee;
import com.MCC53.springsecurity.repositories.DepartmentRepository;
import com.MCC53.springsecurity.repositories.EmployeeRepository;
import com.MCC53.springsecurity.repositories.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author user
 */
@Service
public class EmployeeService {
    
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository, 
            DepartmentRepository departmentRepository) {
            this.employeeRepository = employeeRepository;
            this.departmentRepository = departmentRepository;
            this.projectRepository = projectRepository;
    }
    
    public List<Employee> getAll() {

        return employeeRepository.findAll();
    }
    
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Employee not found"));
    }
    
    public Employee create(Employee employee) {
        System.out.println(employee.getDepartment().toString());
       
        if (employee.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Employee already exist");
        }
         return employeeRepository.save(employee);
    }
    public Employee update(Long id, Employee employee) {
        getById(id);

        employee.setId(id);

        return employeeRepository.save(employee);
    }
     public Employee delete(Long id) {
        Employee employee = getById(id);

        employeeRepository.deleteById(id);
         employee.setProjects(null);

        return employee;
    }

     public List <Employee> findByDepartmentId (Long departmentId) {
        departmentRepository.findById(departmentId)
                .orElseThrow( () ->
                        new ResponseStatusException( HttpStatus.NOT_FOUND,"Department NOt Found"));
    return employeeRepository.findByDepartment_id(departmentId);
    }
     
     public List<Employee> findByProjectId (Long projectId){
        projectRepository.findById(projectId)
                .orElseThrow(() -> 
                            new ResponseStatusException(HttpStatus.NOT_FOUND,"Project Not Found"));
   return  employeeRepository.findByProjects_id(projectId);
    
     }
}
    
