/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.repositories;

import mcc53.com.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xvitas
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    Department findByEmployees_id(Integer id);
}
