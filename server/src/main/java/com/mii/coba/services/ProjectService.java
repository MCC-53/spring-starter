/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.services;

import com.mii.coba.models.Project;
import com.mii.coba.repositories.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author HP
 */
@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    public List<Project> getAllDataProject(){
        return projectRepository.findAll();
    }
    
    public Project getById(Integer id){
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project tidak ditemukan"));
    }
    
    public List<Project> getProjectByLocation(String location){
        return projectRepository.getProjectByLocation(location);
    }
    
    public Project createDataProject(Project project){
        if (project.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Tidak boleh menginputkkan ID");
        }
        return projectRepository.save(project);
    }
    
    public Project updateDataProject (Integer id, Project project){
        Project oldDataProject = getById(id);
        project.setId(oldDataProject.getId());
        
        return projectRepository.save(project);
    }
    
    public Project deleteDataProject (Integer id){
        Project project = getById(id);
        
        projectRepository.deleteById(id);
        
        return project;
    }
}
