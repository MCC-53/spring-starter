package mcc53.com.controllers;

import lombok.AllArgsConstructor;
import mcc53.com.models.Employee;
import mcc53.com.services.EmployeeService;
import mcc53.com.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/employee-project")
public class EmployeeProject {

    private EmployeeService employeeService;
    private ProjectService projectService ;

    @Autowired
    public EmployeeProject(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<Employee>> getByProjectId(
            @PathVariable("id") Long id) {
        return new ResponseEntity(employeeService.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<Employee>> getByEmployeeId(
            @PathVariable("id") Long id) {
        return new ResponseEntity(projectService.findByEmployeeId(id), HttpStatus.OK);
    }



}
