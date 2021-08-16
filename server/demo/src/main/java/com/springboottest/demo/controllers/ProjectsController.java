/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.controllers;
import com.springboottest.demo.models.Projects;
import com.springboottest.demo.services.ProjectsService;
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
 * @author ACER
 */
@RestController
@RequestMapping("/projects")
public class ProjectsController {
    private ProjectsService projectsService;
    @Autowired
    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }
    @GetMapping
    @PreAuthorize("hasAuthority('READ_ALL_PROJECTS')")
    public ResponseEntity<List<Projects>> getAll() {
        return new ResponseEntity(projectsService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_PROJECT_BY_ID')")
    public ResponseEntity<Projects> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(projectsService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_PROJECT')")
    public ResponseEntity<Projects> create(@RequestBody Projects projects) {
        return new ResponseEntity(projectsService.create(projects), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PROJECT')")
    public ResponseEntity<Projects> update(@PathVariable("id") Long id, @RequestBody Projects projects) {
        return new ResponseEntity(projectsService.update(id, projects), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PROJECT')")
    public ResponseEntity<Projects> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(projectsService.delete(id), HttpStatus.OK);
    }
}