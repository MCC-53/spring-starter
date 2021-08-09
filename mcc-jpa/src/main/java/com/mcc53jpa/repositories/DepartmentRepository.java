package com.mcc53jpa.repositories;

import com.mcc53jpa.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByEmployee_id(Long id);
}
