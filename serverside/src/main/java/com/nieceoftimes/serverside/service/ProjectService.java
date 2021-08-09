package com.nieceoftimes.serverside.service;

import com.nieceoftimes.serverside.model.dto.request.ProjectRequest;
import com.nieceoftimes.serverside.model.entity.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(ProjectRequest projectRequest);
    List<Project> readAllProject();
    Project readProjectById(String id);
    List<Project> readProjectByEmployeeId(String id);
    Project updateProjectById(String id, ProjectRequest projectRequest);
    void deleteProjectById(String id);
}
