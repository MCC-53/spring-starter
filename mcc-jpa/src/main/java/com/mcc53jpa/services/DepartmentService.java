package com.mcc53jpa.services;

import com.mcc53jpa.models.Department;
import com.mcc53jpa.models.Employee;
import com.mcc53jpa.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public Department getById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee not found"
                ));

    }

    public Department create(Department department){
        if (department.getId() !=null ){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Department Already Exist"
            );
        }

        return departmentRepository.save(department);
    }

    public Department update(Long id, Department department){
        getById(id);

        department.setId(id);

        return departmentRepository.save(department);
    }

    public Department delete(Long id){
        Department department = getById(id);

        departmentRepository.deleteById(id);
        return department;
    }

    public Department findByEmployeeId(Long id){
        return departmentRepository.findByEmployee_id(id);
    }
}
