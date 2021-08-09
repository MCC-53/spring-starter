/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tugas.com.security.models.Employee;

import javax.websocket.server.PathParam;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    @Query("select e from Employee e where e.firstName = :firstName")
    public Employee findByFirstName(@PathParam("firstName") String firstName);

    @Query("select e from Employee e where e.email LIKE :email")
    public List<Employee> findByEmailLike(@PathParam("email") String email);

    @Query("select e from Employee e where e.department.name LIKE :departmentName")
    public List<Employee> findEmployeeByDepartment(@PathParam("departmentName") String departmentName);

    List<Employee> findByDepartment_id (Long departmentId);
    List<Employee> findByProjects_id (Long projectId);
}
