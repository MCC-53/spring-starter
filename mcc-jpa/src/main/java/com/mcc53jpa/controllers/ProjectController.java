package com.mcc53jpa.controllers;

import com.mcc53jpa.models.Project;
import com.mcc53jpa.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll(){
        return new ResponseEntity<List<Project>>(projectService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project){
        return new ResponseEntity<>(projectService.create(project), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable("id") Long id,@RequestBody Project project){
        return new ResponseEntity<>(projectService.update(id, project), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Project> delete(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.delete(id), HttpStatus.OK);
    }
}
