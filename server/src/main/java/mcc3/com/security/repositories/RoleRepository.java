package mcc3.com.security.repositories;

import java.util.List;
import javax.transaction.Transactional;
import mcc3.com.security.models.entities.Role;
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
public interface RoleRepository extends JpaRepository<Role, Integer>{
    
    @Modifying
    @Query(value = "INSERT INTO role_privilege VALUES (:role_id, :privilege_id);", nativeQuery = true)
    @Transactional
    public void createRolePrivilege(@Param("role_id") Integer roleId, @Param("privilege_id") Integer privilegeId); //OK
}
