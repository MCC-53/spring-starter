/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.repository;

import java.util.List;
import mii.kucoba.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author haikal
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
   
   @Query(value = "select * from employee e where e.name = :name ", nativeQuery = true)
   public Employee getEmployeeByName(@Param("name") String Name);
   
   List<Employee> findByDepartment_id (Integer departmentId);
   List<Employee> findByProjects_id (Integer projectId);
}
