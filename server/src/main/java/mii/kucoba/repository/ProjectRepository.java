/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.repository;

import java.util.List;
import mii.kucoba.models.Project;
import mii.kucoba.models.request.EmployeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abiyo
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
   @Query(value = "select * from project p where p.lokasi = :lokasi and p.name = :name", nativeQuery = true)
   public List<Project> getProjectByLocation(
           @Param("lokasi") String lokasi, 
           @Param("name") String name); 
   
   @Query(value = "SELECT e.`id` idEmployee, e.`name`, e.`email`, e.`address`,e.`last_name`,"
           + "e.`department_id` idDepartment,p.`lokasi` projectLocation,p.`name` "
           + "projectName FROM employee_project ep JOIN `employee` e ON (ep.employee_id = e.`id`) "
           + "JOIN project p ON (ep.project_id = p.`id`)", nativeQuery = true)
   
   public List<EmployeProject> getProjectEmployee(); 
   
   
   
   
   
//   @Modifying
//   @Query(value = "insert into project (lokasi,name) values (:lokasi, :name)", nativeQuery = true)
//   public Project insertProject(@Param("lokasi") String lokasi, @Param("name") String name);
}
