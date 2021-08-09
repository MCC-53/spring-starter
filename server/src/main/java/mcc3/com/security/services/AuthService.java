package mcc3.com.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mcc3.com.security.models.entities.User;
import mcc3.com.security.models.request.AuthResponse;
import mcc3.com.security.models.request.LoginRequest;
import mcc3.com.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author firmanzega
 */
@Service
public class AuthService {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    UserService appUserDetailsService;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserService appUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.appUserDetailsService = appUserDetailsService;
    }
    
    public AuthResponse login(LoginRequest request) {
        AuthResponse auth = new AuthResponse();
        Optional<User> data = userRepository.findByNameuser(request.getNameuser());

        data.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Username not found!"));

        boolean isPasswordMatches = passwordEncoder.matches(
                request.getPassword(), data.get().getPassword());
        if (!isPasswordMatches) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password incorrect!");
        }
        
        String authority = appUserDetailsService.loadUserByUsername(request.getNameuser()).getAuthorities().toString();
        List<String> requestData = new ArrayList<>();
        requestData.add(authority);
        auth.setAuthorities(requestData);

        return auth;
    }

}
