package mcc53.com.services;

import mcc53.com.models.AppUserDetail;
import mcc53.com.repositories.UserRepository;
import mcc53.com.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public AppUserDetailService(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username tidak ditemukan!");
        } return new AppUserDetail(user);
    }
}
