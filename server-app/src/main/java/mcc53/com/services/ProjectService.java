package mcc53.com.services;

import java.util.List;
import mcc53.com.models.Project;
import mcc53.com.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProjectService {
    
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<Project> findByProjectName(String name) {
        return projectRepository.findByProjectName(name);
    }

    public Project findByProjectId(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project not found"));
    }

    public Project findByEmployeesId(Long id) {
        return projectRepository.findByEmployees_id(id);
    }

    public Project createProject(Project project) {
        if(project.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Project already exist");
        }
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        findByProjectId(id);
        project.setId(id);
        return projectRepository.save(project);
    }

    public Project deleteProject(Long id) {
        Project project = findByProjectId(id);
        projectRepository.deleteById(id);
        return project;
    }
}
