/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.Detail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mii.kucoba.models.Privilege;
import mii.kucoba.models.Role;
import mii.kucoba.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author abiyo
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUserDetail implements UserDetails{

    private User user;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
        for (Role role : user.getRoles()){
            grantedAuthoritys.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
            for (Privilege privilege : role.getPrivileges()){
                grantedAuthoritys.add(new SimpleGrantedAuthority(privilege.getName().toUpperCase()));
            }
        }
        
//        return Collections.singleton(new SimpleGrantedAuthority(user.getRoles().toString())); //To change body of generated methods, choose Tools | Templates.
        return grantedAuthoritys;
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
