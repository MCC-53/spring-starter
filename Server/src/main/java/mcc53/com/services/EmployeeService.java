/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import mcc53.com.models.Employee;
import mcc53.com.models.request.ResponseEmployee;
import mcc53.com.repositories.DepartmentRepository;
import mcc53.com.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Xvitas
 */
@Service
public class EmployeeService {
    
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, 
            DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }
    
    public List<Employee> getAlll() {
        return employeeRepository.findAll();
    }
    
    public Employee getById(Integer id) {
      return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                               HttpStatus.NOT_FOUND, "Employee not found"));
        
//        Map<String, Object> data = new HashMap<>();
//        data.put("firstName", employee.getFirstName());
//        data.put("lastName", employee.getLastName());
//        data.put("id", employee.getId());
        
//        return new ResponseEmployee(employee.getFirstName(), 
//                employee.getLastName(), employee.getId());
//        return data;
    }
    
    public Employee create(Employee employee) {
        //ketika client memasukan id ke model/object maka kita anggap data sudah ada
        if (employee.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Employee already exist");
        }
        
        return employeeRepository.save(employee);
    }
    
    public Employee update(Integer id, Employee employee) {
        getById(id);
        
        employee.setId(id);
        
        return employeeRepository.save(employee);
    }
    
    
    public Employee delete(Integer id) {
        Employee employee = getById(id);
        
        employeeRepository.deleteById(id);
        
        employee.setProjects(null);
        
        return employee;
    }
    
    
    public List<Employee> findByDepartmentId(Integer departmentId) {
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> 
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
        
        return employeeRepository.findByDepartment_id(departmentId);
    }
}
