package mcc3.com.security.repositories;

import mcc3.com.security.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author firmanzega
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByEmployees_id(Long id); //OK
}
