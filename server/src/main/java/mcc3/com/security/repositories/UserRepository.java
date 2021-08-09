package mcc3.com.security.repositories;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import mcc3.com.security.models.entities.User;
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
public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByNameuser(String username);
    
    @Modifying
    @Query(value = "INSERT INTO user_role VALUES (:user_id, :role_id);", nativeQuery = true)
    @Transactional
    public void createUserRole(@Param("user_id") Long userId, @Param("role_id") Integer roleId); //OK
}
