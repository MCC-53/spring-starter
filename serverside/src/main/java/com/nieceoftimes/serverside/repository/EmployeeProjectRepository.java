package com.nieceoftimes.serverside.repository;

import com.nieceoftimes.serverside.model.entity.EmployeeProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProjects, String> {
    Boolean existsByEmployeeIdAndProjectId(String employeeId, String projectId);
}
