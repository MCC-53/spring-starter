package tugas.com.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tugas.com.security.models.Role;
import tugas.com.security.models.User;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
