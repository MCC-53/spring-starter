package com.nieceoftimes.clientside.service;

import com.nieceoftimes.clientside.model.Department;
import com.nieceoftimes.clientside.model.ResponseData;
import com.nieceoftimes.clientside.model.ResponseListData;

public interface DepartmentService {
    Department createDepartment(Department department);
    ResponseListData<Department> readAllDepartment();
    ResponseData<Department> readDepartmentById(String id);
    void updateDepartmentById(String id, Department department);
    void deleteDepartmentById(String id);
}
