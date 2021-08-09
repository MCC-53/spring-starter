/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.controllers;

import com.mii.coba.models.Project;
import com.mii.coba.services.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * @author HP
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @GetMapping
    public ResponseEntity<List<Project>> getAllDataProject(){
        return new ResponseEntity(projectService.getAllDataProject(), HttpStatus.OK);
    }
    
    @GetMapping("/{lokasi}")
    public ResponseEntity<List<Project>> getProjectByLocation(@PathVariable("lokasi") String lokasi){
        return new ResponseEntity(projectService.getProjectByLocation(lokasi), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(projectService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Project> createDataProject(@RequestBody Project project){
        return new ResponseEntity<>(projectService.createDataProject(project), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateDataProject(@PathVariable("id") Integer id,
            @RequestBody Project project){
        return new ResponseEntity<>(projectService.updateDataProject(id, project), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteDataProject(@PathVariable("id") Integer id){
        return new ResponseEntity<>(projectService.deleteDataProject(id), HttpStatus.OK);
    }
}
