/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.service.access;

import java.util.List;
import mii.kucoba.models.Project;
import mii.kucoba.models.request.EmployeProject;
import mii.kucoba.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author abiyo
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
    
    public List<Project> getProjectByLocation(String location, String name){
    return projectRepository.getProjectByLocation(location, name);
    }
    
    public List<EmployeProject> getAllProjectEmployee(){
        return projectRepository.getProjectEmployee();
    }
    
    
    public Project createProjectData(Project project){
        if (project.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Project Id Tidak Boleh Diisikan");
        }
        return projectRepository.save(project);
    }
    
    public Project getById(Integer id) {
      return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                               HttpStatus.NOT_FOUND, "Employee not found"));
    }
    
   public Project updateProjectById(Integer id, Project project){
       getById(id);
       project.setId(id);
       return projectRepository.save(project);
   }
   
   public Project DeleteByProjectId(Integer Id){
       Project project = getById(Id);
       projectRepository.deleteById(Id);
       return project;
   }
    
}
