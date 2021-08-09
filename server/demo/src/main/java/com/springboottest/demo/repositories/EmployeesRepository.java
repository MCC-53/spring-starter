/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.repositories;
import com.springboottest.demo.models.Employees;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 *
 * @author ACER
 */
@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    List<Employees> findByDepartments_id(Long id);
    List<Employees> findByProjects_id(Long id);
    Employees findByEmail(String email);
    @Query(value = "SELECT * FROM employees WHERE first_name LIKE ?1", nativeQuery = true)
    Employees findByEmployeesFirstName(String firstName);
}