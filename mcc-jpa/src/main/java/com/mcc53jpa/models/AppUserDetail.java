package com.mcc53jpa.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppUserDetail implements UserDetails {
    private User user;

    public AppUserDetail(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
            for (Privilege privileges : role.getPrivileges()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(privileges.getName().toUpperCase()));
            }
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getIsAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }
}
