/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.repositories;

import com.MCC53.springsecurity.models.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    public Object findById(Long employeeId);
     
    List<Employee> findByDepartment_id (Long departmentId);
    List<Employee> findByProjects_id (Long projectId);
    
}
