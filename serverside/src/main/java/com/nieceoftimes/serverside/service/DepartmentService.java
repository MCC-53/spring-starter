package com.nieceoftimes.serverside.service;

import com.nieceoftimes.serverside.model.dto.request.DepartmentRequest;
import com.nieceoftimes.serverside.model.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(DepartmentRequest departmentRequest);
    List<Department> readAllDepartment();
    Department readDepartmentById(String id);
    Department readDepartmentByEmployeeId(String employeeId);
    Department updateDepartmentById(String id, DepartmentRequest department);
    void deleteDepartmentById(String id);
}
