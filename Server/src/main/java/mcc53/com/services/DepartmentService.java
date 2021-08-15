/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.services;

//import mcc53.com.models.request.EmployeeDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import mcc53.com.models.Department;
import mcc53.com.repositories.DepartmentRepository;
import mcc53.com.repositories.EmployeeRepository;

import java.util.List;

/**
 *
 * @author Xvitas
 */
@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department findByEmployeeId(Integer id) {
        return departmentRepository.findByEmployees_id(id);
    }

    public Department getById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Department not found"));
    }

    public Department create(Department department) {
        if (department.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Department already exist!");
        }

        return departmentRepository.save(department);
    }

    public Department update(Integer id, Department department) {
        getById(id);

        department.setId(id);

        return departmentRepository.save(department);
    }

    public Department delete(Integer id) {
        Department department = getById(id);

        departmentRepository.deleteById(id);

        return department;
    }
}

//    public EmployeeDepartment createDepartmentEmployee(
//            EmployeeDepartment employeeDepartment) {
//
////        if (employeeDepartment.getDepartment().getId() != null) {
////            throw new ResponseStatusException(HttpStatus.CONFLICT, "Department already exist!");
////        }
//
//        /*
//            Create Department
//            Create Employees
//         */
//        
//        Department department = new Department();
//        
////        employeeRepository.save(employeeDepartment.getEmployees().get(0));
//        
////        departmentRepository.save(department);
//
//        return employeeDepartment;
//    }
