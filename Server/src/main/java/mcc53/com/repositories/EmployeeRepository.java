/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.repositories;

import java.util.List;
import mcc53.com.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xvitas
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
    List<Employee> findByDepartment_id (Integer departmentId);
//    List<Employee> findByProjects_id (Integer projectId);
}
