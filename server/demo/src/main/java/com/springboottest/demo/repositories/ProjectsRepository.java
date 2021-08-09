/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.repositories;
import com.springboottest.demo.models.Projects;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author ACER
 */
@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    List<Projects> findByEmployees_id(Long id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employees_project WHERE project_id = ?1", nativeQuery = true)
    void deleteEmployeeProject(Long id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employees_project WHERE employee_id = ?1", nativeQuery = true)
    void deleteEmployeeId(Long id);
}