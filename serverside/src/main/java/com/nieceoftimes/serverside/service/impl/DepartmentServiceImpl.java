package com.nieceoftimes.serverside.service.impl;

import com.nieceoftimes.serverside.model.dto.request.DepartmentRequest;
import com.nieceoftimes.serverside.repository.DepartmentRepository;
import com.nieceoftimes.serverside.model.entity.Department;
import com.nieceoftimes.serverside.service.DepartmentService;
import com.nieceoftimes.serverside.util.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(DepartmentRequest departmentRequest) {
        return departmentRepository.save(
                Department
                .builder()
                .name(departmentRequest.getName())
                .build());
    }

    @Override
    public List<Department> readAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department readDepartmentById(String id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Department not found!", HttpStatus.NOT_FOUND));
    }

    @Override
    public Department readDepartmentByEmployeeId(String employeeId) {
        return departmentRepository.findDepartmentByEmployeesId(employeeId);
    }

    @Override
    public Department updateDepartmentById(String id,
                                           DepartmentRequest departmentRequest) {
        Department departmentUpdate = readDepartmentById(id);

        departmentUpdate.setName(departmentRequest.getName());

        return departmentRepository.save(departmentUpdate);
    }

    @Override
    public void deleteDepartmentById(String id) {
        Department departmentDelete = readDepartmentById(id);

        departmentRepository.delete(departmentDelete);
    }
}
