/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.repositories;

import java.util.List;
import mcc53.com.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WahyuKu
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    List<Project> findByEmployees_id(Long employeeId);
}
