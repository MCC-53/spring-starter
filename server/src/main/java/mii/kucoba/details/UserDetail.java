/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import mii.kucoba.models.Privilege;
import mii.kucoba.models.Role;
import mii.kucoba.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author haikal
 */
public class UserDetail implements UserDetails {
    
    private User user;

    public UserDetail(User user) {
        super();
        this.user = user;
    }
        
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role roles : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(roles.getName()));
            for (Privilege privileges : roles.getPrivileges()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(privileges.getName()));
            }
        }
        return grantedAuthorities;
//        return Collections.singleton(new SimpleGrantedAuthority(user.getRoles().toString())); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        return user.getPassword(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsername() {
        return user.getUsername(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnabled() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
