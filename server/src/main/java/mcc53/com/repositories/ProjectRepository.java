/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.repositories;

import mcc53.com.models.Employee;
import mcc53.com.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author WahyuKu
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

    List<Project>findByEmployees_id(Long employeeId);

    @Query(value = "SELECT * FROM Project p where p.id=:id",nativeQuery = true)
    public Project checkExistId(@Param("id") long id);
}
