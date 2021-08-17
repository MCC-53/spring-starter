/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.controllers;

import com.MCC53.springsecurity.models.Project;
import com.MCC53.springsecurity.models.ResponseMessage;
import com.MCC53.springsecurity.services.ProjectService;
import java.util.List;
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
 * @author user
 */
@RestController
@RequestMapping("/project")
//@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping
//    @PreAuthorize("hasAuthority ('READ_DATA')")
    public ResponseEntity<List<Project>> getAll() {
        return new ResponseEntity(projectService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Project> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(projectService.getById(id), HttpStatus.OK);
    }

    //Custom query : getprojectby location
//    @GetMapping("/{lokasi}")
//    public ResponseEntity<List<Project>> getProjectByLocation(@PathVariable("lokasi") String Lokasi) {
//        return new ResponseEntity(projectService.getProjectByLocation(Lokasi), HttpStatus.OK);
//    }

    @PostMapping
//    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return new ResponseEntity(new ResponseMessage<Project>(projectService.create(project),
                "project created"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Project> update(@PathVariable("id") Long id,
            @RequestBody Project project) {
        return new ResponseEntity(projectService.update(id, project), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority ('CREATE_DATA')")
    public ResponseEntity<Project> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(projectService.delete(id), HttpStatus.OK);
    }
}
