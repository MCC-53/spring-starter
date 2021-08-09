package com.nieceoftimes.serverside.repository;

import com.nieceoftimes.serverside.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    @Query(value = "SELECT * FROM project p INNER JOIN employee_project ep ON p.id = ep.project_id INNER JOIN employee e ON ep.employee_id = e.id WHERE e.id = ?1", nativeQuery = true)
    List<Project> findProjectByEmployeesId(String employeeId);
}
