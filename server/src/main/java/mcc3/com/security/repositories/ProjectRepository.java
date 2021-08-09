package mcc3.com.security.repositories;

import java.util.List;
import mcc3.com.security.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author firmanzega
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByEmployees_id(Long id);
    
    
}
