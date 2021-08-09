/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import mcc53.com.models.Privilege;
import mcc53.com.models.Role;
import mcc53.com.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Xvitas
 */
public class AppUserDetail implements UserDetails {
    
    private User user;

    public AppUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
            for (Privilege privilege : role.getPrivileges()){
                grantedAuthorities.add(new SimpleGrantedAuthority(privilege.getName().toUpperCase()));
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
