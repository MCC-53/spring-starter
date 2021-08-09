/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.repositories;

import mcc53.com.models.Department;
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
public interface DepartmentRepository extends JpaRepository<Department, Long>{

    Department findByEmployees_id(Long id);

    @Query("SELECT d FROM Department d WHERE d.name LIKE %?1%")
    public List<Department> getNameLike(String name);

    @Query("SELECT COUNT(d) FROM Department d WHERE d.name LIKE :name")
    public long countByLikeName(@Param("name") String name);
}
