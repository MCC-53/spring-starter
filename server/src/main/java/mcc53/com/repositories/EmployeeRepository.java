/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.repositories;

import java.util.List;
import mcc53.com.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WahyuKu
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
    List<Employee> findByDepartment_id(Long departmentId);

    List<Employee> findByProjects_id(Long projectId);

    @Query("SELECT e FROM Employee e WHERE e.address is NOT NULL")
    public List<Employee> searchCompleteAddress();

    @Query("SELECT CONCAT('id: ',e.id,'  email: ',e.email) FROM Employee e WHERE e.email LIKE '%_@__%.__%'")
    public List<String> validatingEmailFormat();
}
