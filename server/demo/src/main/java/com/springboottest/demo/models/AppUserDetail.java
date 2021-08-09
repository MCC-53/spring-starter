/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.models;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 *
 * @author ACER
 */
public class AppUserDetail implements UserDetails {
    private Users users;
    public AppUserDetail(Users users) {
        this.users = users;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Roles roles : users.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(roles.getName().toUpperCase()));
            for (Privileges privileges : roles.getPrivileges()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(privileges.getName().toUpperCase()));
            }
        } return grantedAuthorities;
    }
    @Override
    public String getPassword() {
        return users.getPassword();
    }
    @Override
    public String getUsername() {
        return users.getUsername();
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