/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.repositories;

import com.MCC53.springsecurity.models.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    
    List <Project> findByEmployees_id (Long EmployeeId );
    
    List<Project> findByEmployees_id(Integer employeeId);
    
//    @Query (value = "select * from project p where p.lokasi =:lokasi", nativeQuery = true)
//    public List<Project> getProjectByLocation(@Param("lokasi") String lokasi );

    

}
