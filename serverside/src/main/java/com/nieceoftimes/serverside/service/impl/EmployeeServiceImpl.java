package com.nieceoftimes.serverside.service.impl;

import com.nieceoftimes.serverside.model.dto.request.EmployeeUpdateRequest;
import com.nieceoftimes.serverside.model.dto.response.EmployeeUpdateResponse;
import com.nieceoftimes.serverside.model.entity.Department;
import com.nieceoftimes.serverside.model.entity.Employee;
import com.nieceoftimes.serverside.repository.DepartmentRepository;
import com.nieceoftimes.serverside.repository.EmployeeRepository;
import com.nieceoftimes.serverside.service.EmployeeService;
import com.nieceoftimes.serverside.util.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Employee> readAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> readEmployeeByDepartmentId(String departmentId) {
        return employeeRepository.findEmployeeByDepartmentId(departmentId);
    }

    @Override
    public List<Employee> readEmployeeByProjectsId(String projectId) {
        return employeeRepository.findEmployeeByProjectsId(projectId);
    }

    @Override
    public Employee readEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ApiException("Employee not found!", HttpStatus.NOT_FOUND));
    }

    @Override
    public EmployeeUpdateResponse updateEmployeeById(String id, EmployeeUpdateRequest employeeUpdateRequest) {
        Employee employeeUpdate = readEmployeeById(id);
        Department departmentRetrieve = departmentRepository.findById(id)
                        .orElseThrow(() -> new ApiException("Department not found!", HttpStatus.NOT_FOUND));

        employeeUpdate.setFirstName(employeeUpdateRequest.getFirstName());
        employeeUpdate.setLastName(employeeUpdateRequest.getLastName());
        employeeUpdate.setEmail(employeeUpdate.getEmail());
        employeeUpdate.setAddress(employeeUpdate.getAddress());
        employeeUpdate.setDepartment(departmentRetrieve);

        employeeRepository.save(employeeUpdate);

        return EmployeeUpdateResponse
                .builder()
                .firstName(employeeUpdate.getFirstName())
                .lastName(employeeUpdate.getLastName())
                .email(employeeUpdate.getEmail())
                .address(employeeUpdate.getAddress())
                .department(employeeUpdate.getDepartment())
                .build();
    }

    @Override
    public void deleteEmployeeById(String id) {
        Employee employeeDelete = readEmployeeById(id);

        employeeRepository.delete(employeeDelete);
    }
}
