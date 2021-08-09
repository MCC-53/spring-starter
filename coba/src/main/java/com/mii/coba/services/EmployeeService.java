/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.services;

import com.mii.coba.models.Employee;
import com.mii.coba.repositories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author HP
 */
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }
    
    public Employee getById(Integer id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee tidak ditemukan"));
    }
    
    public Employee insertDataEmployee(Employee employee){
        
        if(employee.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tidak boleh menginputkkan ID");
        }
            
        return employeeRepository.save(employee);
        
    }
    
    public Employee update(Integer id, Employee employee){
        Employee oldDataEmployee = getById(id);
        employee.setId(oldDataEmployee.getId());
        
        return employeeRepository.save(employee);
    }
    
    public Employee delete(Integer id){
        Employee employee = getById(id);
        employeeRepository.deleteById(id);
        
        return employee;
    }
    
    public Employee findEmployeeByDepartmentId(Integer departmentId){
        return employeeRepository.findByDepartment_id(departmentId);
    }
}
