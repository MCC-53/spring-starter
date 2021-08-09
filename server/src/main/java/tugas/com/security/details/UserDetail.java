package tugas.com.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tugas.com.security.models.Role;
import tugas.com.security.models.User;
import tugas.com.security.models.Privilege;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {
    private User user;

    public UserDetail(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role:user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()));
            for (Privilege privilege:role.getPrivileges()) {
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
