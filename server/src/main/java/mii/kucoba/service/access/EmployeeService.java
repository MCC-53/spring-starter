/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.service.access;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import mii.kucoba.models.Department;
import mii.kucoba.models.Employee;
import mii.kucoba.repository.DepartmentRepository;
import mii.kucoba.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author abiyo
 */
//@AllArgsConstructor
@Service
public class EmployeeService {
    
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
        
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }
    
    public Employee insertDataEmployee(Employee employee){
        if(employee.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tidak boleh menginputkkan ID");
        }
        return employeeRepository.save(employee);
    }
    
    public Employee getById(Integer id) {
      return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                               HttpStatus.NOT_FOUND, "Employee Tidak Ditemukan"));
    }
    
    public Employee getEmployeeByName(String name) {
        return employeeRepository.getEmployeeByName(name);
    }
    
    public Employee update(Integer id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }
    
    public Employee delete(Integer id) {
        Employee employee = getById(id);
        employeeRepository.deleteById(id);
        return employee;
    }
   
    public List<Employee> getEmployeeByDepartment(Integer departmentId){
        return employeeRepository.findByDepartment_id(departmentId);
    }
    
}
