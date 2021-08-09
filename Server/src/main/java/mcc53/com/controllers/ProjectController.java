/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Project;
import mcc53.com.models.Project;
import mcc53.com.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xvitas
 */
@RestController
@RequestMapping("/project")
//@PreAuthorize("hasAnyRole('admin', 'employee')")
public class ProjectController {
    
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @GetMapping
//    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Project>> getAll() {
        return new ResponseEntity(projectService.getAll(), HttpStatus.OK);
    }
    
     @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Project> getById(
            @PathVariable("id") Integer id) {
        return new ResponseEntity(projectService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return new ResponseEntity(projectService.create(project), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Project> update(
            @PathVariable("id") Integer id, @RequestBody Project project) {
        return new ResponseEntity(projectService.update(id, project), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Project> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity(projectService.delete(id), HttpStatus.OK);
    }
}

