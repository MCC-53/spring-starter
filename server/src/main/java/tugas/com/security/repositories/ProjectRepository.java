package tugas.com.security.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import tugas.com.security.models.Department;
import tugas.com.security.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    List<Project> findByEmployees_Id(Long id);
}
