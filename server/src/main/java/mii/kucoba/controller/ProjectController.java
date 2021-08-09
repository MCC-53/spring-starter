/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.controller;

import java.util.List;
import mii.kucoba.config.ResponseMessage;
import mii.kucoba.models.Project;
import mii.kucoba.repository.ProjectRepository;
import mii.kucoba.service.ProjectService;
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
 * @author haikal
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
    
    @GetMapping("/showid/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") Integer id){  
        return new ResponseEntity(projectService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping("/showlocation/{lokasi}")
    public ResponseEntity<List<Project>> getProjectByLocation(@PathVariable("lokasi") String lokasi){
        return new ResponseEntity(projectService.getProjectByLocation(lokasi), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        return new ResponseEntity(new ResponseMessage<Project>(projectService.createProjectData(project), "Success"), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Project> UpdateProjectById(@PathVariable("id") Integer id,
            @RequestBody Project project){
        return new ResponseEntity(new ResponseMessage<Project>(projectService.updateProjectById(id, project), "Success"), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Project> DeleteById(@PathVariable("id") Integer id){
        return new ResponseEntity(new ResponseMessage<Project>(projectService.DeleteByProjectId(id), "Success"), HttpStatus.OK);
    }
}
