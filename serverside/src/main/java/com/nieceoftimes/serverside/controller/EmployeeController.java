package com.nieceoftimes.serverside.controller;

import com.nieceoftimes.serverside.model.dto.request.EmployeeUpdateRequest;
import com.nieceoftimes.serverside.service.impl.EmployeeServiceImpl;
import com.nieceoftimes.serverside.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employee")
//@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class EmployeeController {
    private EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Object> readAllEmployee() {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read all data employee",
                        employeeService.readAllEmployee()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readEmployeeById(@PathVariable String id) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read employee data by id",
                        employeeService.readEmployeeById(id)),
                HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Object> readEmployeeByDepartmentId(@PathVariable String departmentId) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read employee data by department id",
                        employeeService.readEmployeeByDepartmentId(departmentId)),
                HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Object> readEmployeeByProjectId(@PathVariable String projectId) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read employee data by project id",
                        employeeService.readEmployeeByProjectsId(projectId)),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployeeById(@PathVariable String id,
                                                     @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully updated employee data",
                        employeeService.updateEmployeeById(id, employeeUpdateRequest)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable String id) {
        employeeService.deleteEmployeeById(id);

        return new ResponseEntity<>(
                new ApiResponse("Successfully deleted employee data"),
                HttpStatus.OK);
    }

}
