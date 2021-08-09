/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.details;

import mcc53.com.details.AppUserDetail;
import mcc53.com.repositories.UserRepository;
import mcc53.com.models.User;
import mcc53.com.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xvitas
 */
@Service
public class AppUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user with username '%s' not found");
        }
        return new AppUserDetail(user); 
    }
}
