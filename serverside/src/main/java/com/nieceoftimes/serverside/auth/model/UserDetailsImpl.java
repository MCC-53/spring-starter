package com.nieceoftimes.serverside.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nieceoftimes.serverside.model.entity.Privilege;
import com.nieceoftimes.serverside.model.entity.Role;
import com.nieceoftimes.serverside.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private User user;
    public UserDetailsImpl(User user) {
        this.user = user;
    }
    private String id;
    private String username;
    @JsonIgnore
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().name()));
            for (Privilege privilege : role.getPrivileges()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(privilege.getName().name()));
            }
        }

        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

