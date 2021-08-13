package mcc53.com.repositories;

import mcc53.com.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    Project findByEmployees_id(Long id);

    @Query(value = "SELECT * FROM Project WHERE name = :name ", nativeQuery = true)
    List<Project> findByProjectName(@Param("name") String name);
}
