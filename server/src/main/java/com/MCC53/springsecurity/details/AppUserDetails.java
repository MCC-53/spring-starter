/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.details;

import com.MCC53.springsecurity.models.Privilege;
import com.MCC53.springsecurity.models.Role;
import com.MCC53.springsecurity.models.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author user
 */
public class AppUserDetails implements UserDetails {

    private User user;

    public AppUserDetails(User user) {
        super();
        this.user = user;
    }

    @Override //get authority : role dan privileges
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorites = new ArrayList<>();
        for (Role roles : user.getRoles()){
            grantedAuthorites.add(new SimpleGrantedAuthority("ROLE_"+roles.getName().toUpperCase()));
            for (Privilege privileges : roles.getPrivileges()){
                grantedAuthorites.add(new SimpleGrantedAuthority(privileges.getName().toUpperCase()));
            }
        }
        return grantedAuthorites;
        
//        return Collections.singleton(new SimpleGrantedAuthority(user.getRoles().toString()));
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
