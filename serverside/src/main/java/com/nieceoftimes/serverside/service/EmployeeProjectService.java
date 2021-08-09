package com.nieceoftimes.serverside.service;

import com.nieceoftimes.serverside.model.dto.request.EmployeeProjectRequest;
import com.nieceoftimes.serverside.model.entity.EmployeeProjects;

public interface EmployeeProjectService {
    EmployeeProjects createEmployeeProject(EmployeeProjectRequest employeeProjectRequest);
}
