package com.nieceoftimes.serverside.repository;

import com.nieceoftimes.serverside.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    @Query(value = "SELECT * FROM department d INNER JOIN employee e ON d.id = e.department_id WHERE e.id = ?1", nativeQuery = true)
    Department findDepartmentByEmployeesId(String employeeId);
}
