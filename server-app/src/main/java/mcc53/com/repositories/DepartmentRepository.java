package mcc53.com.repositories;

import java.util.List;
import mcc53.com.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
    Department findByEmployees_id(Long id);
    
    @Query(value = "SELECT * FROM Department WHERE name = :name", nativeQuery = true)
    List<Department> findByDepartmentName(@Param("name") String name);

}
