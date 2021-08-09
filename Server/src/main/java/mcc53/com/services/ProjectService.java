/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.services;

import java.util.List;
import mcc53.com.models.Project;
import mcc53.com.models.Project;
import mcc53.com.models.Project;
import mcc53.com.repositories.EmployeeRepository;
import mcc53.com.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Xvitas
 */
@Service
public class ProjectService {

    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }
    
    public Project getById(Integer id) {
      return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                               HttpStatus.NOT_FOUND, "Employee not found"));
    }
    
    public Project create(Project project) {
        if (project.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Project already exist!");
        }
        
        return projectRepository.save(project);
    }
    
    public Project update(Integer id, Project project) {
        getById(id);
        
        project.setId(id);
        
        return projectRepository.save(project);
    }
    
    public Project delete(Integer id) {
        Project project = getById(id);
        
        projectRepository.deleteById(id);
        
        return project;
    }

    public List<Project> findByEmployeeId(Integer id) {
        employeeRepository.findById(id)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        return projectRepository.findByEmployees_id(id);
    }
}
