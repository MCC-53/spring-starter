package com.nieceoftimes.serverside.controller;

import com.nieceoftimes.serverside.model.dto.request.DepartmentRequest;
import com.nieceoftimes.serverside.service.impl.DepartmentServiceImpl;
import com.nieceoftimes.serverside.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
//@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class DepartmentController {
    private DepartmentServiceImpl departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Object> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully created data",
                        departmentService.createDepartment(departmentRequest)),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> readAllDepartment() {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read all data department",
                        departmentService.readAllDepartment()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readDepartmentById(@PathVariable String id) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read department by id",
                        departmentService.readDepartmentById(id)),
                HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Object> readDepartmentByEmployeeId(@PathVariable String employeeId) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read department by employee id",
                        departmentService.readDepartmentByEmployeeId(employeeId)),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDepartmentById(@PathVariable String id,
                                                       @RequestBody DepartmentRequest departmentRequest) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully updated data",
                        departmentService.updateDepartmentById(id, departmentRequest)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartmentById(@PathVariable String id) {
        departmentService.deleteDepartmentById(id);

        return new ResponseEntity<>(
                new ApiResponse("Successfully deleted data"),
                HttpStatus.OK);
    }
}
