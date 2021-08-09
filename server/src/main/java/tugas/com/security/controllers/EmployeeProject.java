package tugas.com.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tugas.com.security.models.Employee;
import tugas.com.security.models.Project;
import tugas.com.security.services.EmployeeService;
import tugas.com.security.services.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/employee-project")
public class EmployeeProject {
    private EmployeeService employeeService;
    private ProjectService projectService;

    @Autowired
    public EmployeeProject(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("project/{id}")
    public ResponseEntity<List<Employee>> getByProjecttId(
            @PathVariable("id") Long projecttId) {
        return new ResponseEntity(employeeService.findByProjectsId(projecttId), HttpStatus.OK);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<List<Project>> getByEmployeeId(
            @PathVariable("id") Long employeeId) {
        return new ResponseEntity(projectService.findByEmployeesId(employeeId), HttpStatus.OK);
    }
}
