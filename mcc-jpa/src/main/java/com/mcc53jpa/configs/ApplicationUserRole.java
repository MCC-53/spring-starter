package com.mcc53jpa.configs;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.mcc53jpa.configs.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    EMPLOYEE(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(DEPARTMENT_READ, DEPARTMENT_WRITE,EMPLOYEE_READ,EMPLOYEE_WRITE)),
    ADMINMAGANG(Sets.newHashSet(DEPARTMENT_READ, EMPLOYEE_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permission = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());

        permission.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permission;
    }
}
