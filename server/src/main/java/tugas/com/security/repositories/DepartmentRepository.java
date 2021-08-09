package tugas.com.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas.com.security.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
    Department findByEmployees_id(Long id);
}
