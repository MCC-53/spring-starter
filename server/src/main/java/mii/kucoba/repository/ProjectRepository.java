/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.repository;

import java.util.List;
import mii.kucoba.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author haikal
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
   @Query(value = "select * from project p where p.lokasi LIKE %:lokasi%", nativeQuery = true)
   public List<Project> getProjectByLocation(@Param("lokasi") String lokasi); 
   
}
