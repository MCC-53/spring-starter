package com.nieceoftimes.serverside.controller;

import com.nieceoftimes.serverside.model.dto.request.ProjectRequest;
import com.nieceoftimes.serverside.service.impl.ProjectServiceImpl;
import com.nieceoftimes.serverside.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class ProjectController {
    private ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project")
    public ResponseEntity<Object> createProject(@RequestBody ProjectRequest projectRequest) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully created project data",
                        projectService.createProject(projectRequest)),
                HttpStatus.CREATED);
    }

    @GetMapping("/project")
    public ResponseEntity<Object> readAllProject() {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read all project data",
                        projectService.readAllProject()),
                HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Object> readProjectById(@PathVariable String id) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read project data by id",
                        projectService.readProjectById(id)),
                HttpStatus.OK);
    }

    @GetMapping("/project-employee/{employeeId}")
    public ResponseEntity<Object> readProjectByEmployeeId(@PathVariable String employeeId) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully read project data by employee id",
                        projectService.readProjectByEmployeeId(employeeId)),
                HttpStatus.OK);
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<Object> updateProjectById(@PathVariable String id,
                                                    @RequestBody ProjectRequest projectRequest) {
        return new ResponseEntity<>(
                new ApiResponse("Successfully updated project data",
                        projectService.updateProjectById(id, projectRequest)),
                HttpStatus.OK);
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<Object> deleteProjectById(@PathVariable String id) {
        projectService.deleteProjectById(id);

        return new ResponseEntity<>(
                new ApiResponse("Successfully deleted project data"),
                HttpStatus.OK);
    }
}
