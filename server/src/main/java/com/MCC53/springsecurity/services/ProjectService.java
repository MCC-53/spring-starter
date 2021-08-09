/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.services;

import com.MCC53.springsecurity.models.Project;
import com.MCC53.springsecurity.repositories.EmployeeRepository;
import com.MCC53.springsecurity.repositories.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author user
 */
@Service
public class ProjectService {
  
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  
  public List<Project> getAll() {

        return projectRepository.findAll();
    }

    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Project not found"));
    }
    
    public Project create(Project project) {
//        System.out.println(project.getEmployees().toString());

        if (project.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data Project Sudah ada");
        }
        return projectRepository.save(project);
    }
   
    public Project update ( Long id , Project project){
        getById(id);
        
        project.setId(id); //set id yg upted
        
        return projectRepository.save(project);
    }
    public Project delete ( Long id){
        Project project = getById(id);
        
         projectRepository.deleteById(id); //delete data by id
         
         return project;
    }
    
    public List <Project> findByEmployeeId(Long id){
        employeeRepository.findById(id)                
                .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND," Data employee tidak ditemukan"));

        return projectRepository.findByEmployees_id(id);
    }
    //custom query
//    public List<Project> getProjectByLocation(String location){
//        return projectRepository.getProjectByLocation(location);
//    }

}
