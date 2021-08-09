package com.mcc53jpa.services;

import com.mcc53jpa.models.Employee;
import com.mcc53jpa.repositories.DepartmentRepository;
import com.mcc53jpa.repositories.EmployeeRepository;
import com.mcc53jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private UserRepository userRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, UserRepository userRepository){
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee getById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee not found"
                ));
    }

    public Employee create(Employee employee){
        System.out.println(employee.getDepartment().toString());
        if (employee.getId() !=null ){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Employee Already Exist"
            );
        }

        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee employee){
        getById(id);

        employee.setId(id);

        return employeeRepository.save(employee);
    }

    public Employee delete(Long id){
        Employee employee = getById(id);

        employeeRepository.deleteById(id);
        return employee;
    }

    public List<Employee> findByDepartmentId(Long id){
        departmentRepository.findById(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
        return employeeRepository.findByDepartment_id(id);
    }

    public List<Employee> findEmployeeWithName(String name){
        if (name == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "nama tidak ditemukan");
        }
       return employeeRepository.findEmployeeWithName(name);
    }
}
