package mcc53.com.repositories;

import java.util.List;
import mcc53.com.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
    List<Employee> findByDepartment_id (Long departmentId);
}
