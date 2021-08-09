package com.mcc53jpa.services;

import com.mcc53jpa.models.AppUserDetail;
import com.mcc53jpa.models.User;
import com.mcc53jpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (username == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new AppUserDetail(user);
    }
}
