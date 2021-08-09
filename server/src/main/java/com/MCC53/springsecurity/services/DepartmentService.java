/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.services;

import com.MCC53.springsecurity.models.Department;
import com.MCC53.springsecurity.repositories.DepartmentRepository;
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
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    //getAll : Department
    public List<Department> getAll() {

        return departmentRepository.findAll();
    }

    //getbyid :department/id/
    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "department not found"));
    }
    
    //findEmployee by department/onetomany
     public Department findByEmployeeId(Long id) {

        return departmentRepository.findByEmployees_id(id);
    }

     //insert 
     public Department create(Department department) {
        if (department.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Department Already Exist");
        }
        return departmentRepository.save(department);
    }
     //update / put
     public Department update(Long id, Department department) {

        getById(id);
        department.setId(id);

        return departmentRepository.save(department);
    }
     //delete / id
     public Department delete(Long id){
        
        Department department = getById(id);
        
        departmentRepository.deleteById(id);
        
        return department;
    }
}
