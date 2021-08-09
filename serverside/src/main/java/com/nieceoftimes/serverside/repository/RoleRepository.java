package com.nieceoftimes.serverside.repository;

import com.nieceoftimes.serverside.enums.RoleName;
import com.nieceoftimes.serverside.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findRoleByName(RoleName roleName);
}
