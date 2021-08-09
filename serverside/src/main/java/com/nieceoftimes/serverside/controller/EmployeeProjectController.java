package com.nieceoftimes.serverside.controller;

import com.nieceoftimes.serverside.model.dto.request.EmployeeProjectRequest;
import com.nieceoftimes.serverside.service.impl.EmployeeProjectServiceImpl;
import com.nieceoftimes.serverside.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee-project")
public class EmployeeProjectController {
    private EmployeeProjectServiceImpl employeeProjectService;

    @Autowired
    public EmployeeProjectController(EmployeeProjectServiceImpl employeeProjectService) {
        this.employeeProjectService = employeeProjectService;
    }

    @PostMapping
    public ResponseEntity<Object> createEmployeeProject(@RequestBody EmployeeProjectRequest employeeProjectRequest) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully created employee project",
                        employeeProjectService.createEmployeeProject(employeeProjectRequest)),
                        HttpStatus.CREATED);
    }

}
