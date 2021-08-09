package com.nieceoftimes.serverside.service.impl;

import com.nieceoftimes.serverside.model.dto.request.EmployeeProjectRequest;
import com.nieceoftimes.serverside.model.entity.Employee;
import com.nieceoftimes.serverside.model.entity.EmployeeProjects;
import com.nieceoftimes.serverside.model.entity.Project;
import com.nieceoftimes.serverside.repository.EmployeeProjectRepository;
import com.nieceoftimes.serverside.repository.EmployeeRepository;
import com.nieceoftimes.serverside.repository.ProjectRepository;
import com.nieceoftimes.serverside.service.EmployeeProjectService;
import com.nieceoftimes.serverside.util.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {
    private EmployeeProjectRepository employeeProjectRepository;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public EmployeeProjectServiceImpl(EmployeeProjectRepository employeeProjectRepository,
                                      EmployeeRepository employeeRepository,
                                      ProjectRepository projectRepository) {
        this.employeeProjectRepository = employeeProjectRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    private Boolean isEmployeeAndProjectExist(String employeeId, String projectId) {
        return employeeProjectRepository
                .existsByEmployeeIdAndProjectId(employeeId, projectId);
    }

    @Override
    public EmployeeProjects createEmployeeProject(EmployeeProjectRequest employeeProjectRequest) {
        Employee employeeRetrieve = employeeRepository.findById(employeeProjectRequest.getEmployeeId())
                .orElseThrow(() -> new ApiException("Employee not found!", HttpStatus.CONFLICT));
        Project projectRetrieve = projectRepository.findById(employeeProjectRequest.getProjectId())
                .orElseThrow(() -> new ApiException("Project not found!", HttpStatus.CONFLICT));

        if (isEmployeeAndProjectExist(employeeProjectRequest.getEmployeeId(), employeeProjectRequest.getProjectId())) {
            throw new ApiException("Employee id and project id already exists!", HttpStatus.CONFLICT);
        } else {
            return employeeProjectRepository.save(
                    EmployeeProjects
                            .builder()
                            .employee(employeeRetrieve)
                            .project(projectRetrieve)
                            .build());
        }
    }
}
