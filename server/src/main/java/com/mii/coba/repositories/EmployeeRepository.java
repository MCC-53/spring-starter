/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.coba.repositories;

import com.mii.coba.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
    Employee findByDepartment_id(Integer departmentId);
}
