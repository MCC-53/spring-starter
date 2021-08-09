package tugas.com.security.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tugas.com.security.models.User;
import tugas.com.security.repositories.UserRepository;

import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class UserDetailService implements UserDetailsService {

    private UserRepository _userRepository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail user = new UserDetail(_userRepository.findByUsername(username)
                .orElseThrow(() -> 
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Username tidak ditemukan")));
        
        return user;
    }
}
