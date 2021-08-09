package mcc3.com.security.repositories;

import java.util.List;
import javax.transaction.Transactional;
import mcc3.com.security.models.entities.Employee;
import mcc3.com.security.models.request.RegisterData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author firmanzega
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment_id(Long departmentId); //OK

    List<Employee> findByProjects_id(Long projectId); //OK

    @Query(value = "SELECT e FROM Employee e WHERE lastName = :last_name")
    public List<Employee> findByLastName(@Param("last_name") String lastName); //OK
    
    @Modifying
    @Query(value = "INSERT INTO employee_project VALUES (:employee_id, :project_id);",nativeQuery = true)
    @Transactional
    public void createEmployeeProject(@Param("employee_id") Long employeeId, @Param("project_id") Long projectId); //OK
}
