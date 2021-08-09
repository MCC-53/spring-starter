package com.nieceoftimes.serverside.seeder;

import com.nieceoftimes.serverside.enums.PrivilegeAuthority;
import com.nieceoftimes.serverside.model.entity.Privilege;
import com.nieceoftimes.serverside.model.entity.Role;
import com.nieceoftimes.serverside.repository.PrivilegeRepository;
import com.nieceoftimes.serverside.repository.RoleRepository;
import com.nieceoftimes.serverside.util.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static com.nieceoftimes.serverside.enums.PrivilegeAuthority.*;
import static com.nieceoftimes.serverside.enums.RoleName.ROLE_ADMIN;
import static com.nieceoftimes.serverside.enums.RoleName.ROLE_USER;

@Component
public class SeedDataEntity implements CommandLineRunner {
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;

    @Autowired
    public SeedDataEntity(RoleRepository roleRepository,
                          PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        seedDataPrivilege();
        seedDataRole();
    }

    public void seedDataPrivilege() {
        if (privilegeRepository.count() == 0) {
            seedPrivilegeAuthority(CREATE_DEPARTMENT);
            seedPrivilegeAuthority(READ_DEPARTMENT);
            seedPrivilegeAuthority(UPDATE_DEPARTMENT);
            seedPrivilegeAuthority(DELETE_DEPARTMENT);
            seedPrivilegeAuthority(CREATE_PROJECT);
            seedPrivilegeAuthority(READ_PROJECT);
            seedPrivilegeAuthority(UPDATE_PROJECT);
            seedPrivilegeAuthority(DELETE_PROJECT);
            seedPrivilegeAuthority(READ_EMPLOYEE);
            seedPrivilegeAuthority(UPDATE_EMPLOYEE);
            seedPrivilegeAuthority(DELETE_EMPLOYEE);
        }
    }

    private void seedPrivilegeAuthority(PrivilegeAuthority privilegeAuthority) {
        Privilege privilege = Privilege
                .builder()
                .name(privilegeAuthority)
                .build();
        privilegeRepository.save(privilege);
    }

    public void seedDataRole() {

        Set<Privilege> userPrivilegeSet = new HashSet<>();
        userPrivilegeAddition(userPrivilegeSet, READ_DEPARTMENT);
        userPrivilegeAddition(userPrivilegeSet, READ_PROJECT);
        userPrivilegeAddition(userPrivilegeSet, UPDATE_PROJECT);
        userPrivilegeAddition(userPrivilegeSet, READ_EMPLOYEE);
        userPrivilegeAddition(userPrivilegeSet, UPDATE_EMPLOYEE);
        userPrivilegeAddition(userPrivilegeSet, DELETE_EMPLOYEE);

        Set<Privilege> adminPrivilegeSet = new HashSet<>();
        adminPrivilegeAddition(adminPrivilegeSet, CREATE_DEPARTMENT);
        adminPrivilegeAddition(adminPrivilegeSet, READ_DEPARTMENT);
        adminPrivilegeAddition(adminPrivilegeSet, UPDATE_DEPARTMENT);
        adminPrivilegeAddition(adminPrivilegeSet, DELETE_DEPARTMENT);
        adminPrivilegeAddition(adminPrivilegeSet, CREATE_PROJECT);
        adminPrivilegeAddition(adminPrivilegeSet, READ_PROJECT);
        adminPrivilegeAddition(adminPrivilegeSet, UPDATE_PROJECT);
        adminPrivilegeAddition(adminPrivilegeSet, DELETE_PROJECT);
        adminPrivilegeAddition(adminPrivilegeSet, READ_EMPLOYEE);

        if (roleRepository.count() == 0) {
            Role roleUser = Role
                    .builder()
                    .name(ROLE_USER)
                    .privileges(userPrivilegeSet)
                    .build();
            roleRepository.save(roleUser);

            Role roleAdmin = Role
                    .builder()
                    .name(ROLE_ADMIN)
                    .privileges(adminPrivilegeSet)
                    .build();
            roleRepository.save(roleAdmin);
        }
    }

    private void userPrivilegeAddition(Set<Privilege> userPrivilegeSet, PrivilegeAuthority privilegeAuthority) {
        userPrivilegeSet.add(privilegeRepository.findPrivilegeByName(privilegeAuthority).orElseThrow(() -> new ApiException("Privilege not found!", HttpStatus.NOT_FOUND)));
    }

    private void adminPrivilegeAddition(Set<Privilege> adminPrivilegeSet, PrivilegeAuthority privilegeAuthority) {
        adminPrivilegeSet.add(privilegeRepository.findPrivilegeByName(privilegeAuthority).orElseThrow(() -> new ApiException("Privilege not found!", HttpStatus.NOT_FOUND)));
    }
}
