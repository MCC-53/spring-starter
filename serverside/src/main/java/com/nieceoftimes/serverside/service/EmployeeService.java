package com.nieceoftimes.serverside.service;

import com.nieceoftimes.serverside.model.dto.request.EmployeeUpdateRequest;
import com.nieceoftimes.serverside.model.dto.response.EmployeeUpdateResponse;
import com.nieceoftimes.serverside.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> readAllEmployee();
    List<Employee> readEmployeeByDepartmentId(String departmentId);
    List<Employee> readEmployeeByProjectsId(String projectId);
    Employee readEmployeeById(String id);
    EmployeeUpdateResponse updateEmployeeById(String id, EmployeeUpdateRequest employeeUpdateRequest);
    void deleteEmployeeById(String id);
}
