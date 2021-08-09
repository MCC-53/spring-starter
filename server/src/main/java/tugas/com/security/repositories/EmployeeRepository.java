/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas.com.security.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
    List<Employee> findByDepartment_id (Long departmentId);
//    List<Employee> findByProjects_id (Long projectId);
}
