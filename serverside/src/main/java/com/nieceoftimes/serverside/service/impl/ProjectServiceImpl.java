package com.nieceoftimes.serverside.service.impl;

import com.nieceoftimes.serverside.model.dto.request.ProjectRequest;
import com.nieceoftimes.serverside.model.entity.Project;
import com.nieceoftimes.serverside.repository.ProjectRepository;
import com.nieceoftimes.serverside.service.ProjectService;
import com.nieceoftimes.serverside.util.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(ProjectRequest projectRequest) {
        return projectRepository.save(
                Project
                .builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .build());
    }

    @Override
    public List<Project> readAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public Project readProjectById(String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ApiException("Project not found!", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Project> readProjectByEmployeeId(String id) {
        return projectRepository.findProjectByEmployeesId(id);
    }

    @Override
    public Project updateProjectById(String id, ProjectRequest projectRequest) {
        Project projectUpdate = readProjectById(id);

        projectUpdate.setName(projectRequest.getName());
        projectUpdate.setDescription(projectRequest.getDescription());

        return projectRepository.save(projectUpdate);
    }

    @Override
    public void deleteProjectById(String id) {
        Project projectDelete = readProjectById(id);

        projectRepository.delete(projectDelete);
    }
}
