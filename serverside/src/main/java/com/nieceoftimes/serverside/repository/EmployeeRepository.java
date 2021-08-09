package com.nieceoftimes.serverside.repository;

import com.nieceoftimes.serverside.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findEmployeeByDepartmentId(String departmentId);

    @Query(value = "SELECT * FROM employee e INNER JOIN employee_project ep ON e.id = ep.employee_id INNER JOIN project p ON ep.project_id = p.id  WHERE p.id = ?1", nativeQuery = true)
    List<Employee> findEmployeeByProjectsId(String projectId);
}
