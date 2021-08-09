package com.nieceoftimes.serverside.repository;

import com.nieceoftimes.serverside.enums.PrivilegeAuthority;
import com.nieceoftimes.serverside.model.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, String> {
    Optional<Privilege> findPrivilegeByName(PrivilegeAuthority privilegeAuthority);
}
