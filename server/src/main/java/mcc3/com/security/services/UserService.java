package mcc3.com.security.services;

import java.util.List;
import javax.transaction.Transactional;
import mcc3.com.security.models.entities.User;
import mcc3.com.security.repositories.RoleRepository;
import mcc3.com.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author firmanzega
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    
    
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username '%s' not found!", username)));
        
//        Optional<User> user = userRepository.findByUsername(username);
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role roles : user.get().getRoles()){
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getName()));
//            for (Privilege privileges : roles.getPrivileges()) {
//                authorities.add(new SimpleGrantedAuthority(privileges.getName()));
//            }
//        }
//        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
        
//        List<String> requestData = appUserDetailsService.loadUserByUsername(request.getUsername()).getAuthorities()
//                .stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
    }
    
    public User getById(Long id) {//OK
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Role not found!"));
    }
    
    public List<User> getAll() {
        return userRepository.findAll();
    }
    
    public void createUserRole(Long userId, Integer roleId){
        roleRepository.findById(roleId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found!"));
        userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found!"));
        
        userRepository.createUserRole(userId, roleId);
    }
}
