package com.mcc53jpa.services;

import com.mcc53jpa.models.Project;
import com.mcc53jpa.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    public Project getById(Long id){
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project %s not Found" + id));
    }

    public Project create(Project project){
        if (project.getProjectId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Project Id Already Uses");
        }
        return projectRepository.save(project);
    }

    public Project update(Long id, Project project){
        getById(id);

        project.setProjectId(id);

        return projectRepository.save(project);
    }

    public Project delete(Long id){
        Project project = getById(id);

        projectRepository.deleteById(id);
        return project;
    }
}
