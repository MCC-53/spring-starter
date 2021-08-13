package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Project;
import mcc53.com.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class ProjectController {
    
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @GetMapping
//    @PreAuthorize("hasAuthority('READ_PROJECT')")
    public ResponseEntity<List<Project>> getAll() {
        return new ResponseEntity(projectService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
//    @PreAuthorize("hasAuthority('READ_PROJECT_BY_NAME')")
    public ResponseEntity<Project> findByProjectName(@PathVariable("name") String name) {
        return new ResponseEntity(projectService.findByProjectName(name), HttpStatus.FOUND);
    }

    @GetMapping("/id/{id}")
//    @PreAuthorize("hasAuthority('READ_PROJECT_BY_ID')")
    public ResponseEntity<Project> findByProjectId(@PathVariable("id") Long id) {
        return new ResponseEntity(projectService.findByProjectId(id), HttpStatus.FOUND);
    }

    @GetMapping("/employee/{id}")
//    @PreAuthorize("hasAuthority('READ_PROJECT_BY_EMPLOYEE_ID')")
    public ResponseEntity<Project> findByEmployeeId(@PathVariable("id") Long id) {
        return new ResponseEntity(projectService.findByEmployeesId(id), HttpStatus.FOUND);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE_PROJECT')")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return new ResponseEntity(projectService.createProject(project), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('UPDATE_PROJECT')")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, @RequestBody Project project) {
        return new ResponseEntity(projectService.updateProject(id, project), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE_PROJECT')")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id) {
        return new ResponseEntity(projectService.deleteProject(id), HttpStatus.GONE);
    }
}
