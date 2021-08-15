package tugas.com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tugas.com.security.details.UserDetailService;
import tugas.com.security.models.User;
import tugas.com.security.models.request.AuthRequest;
import tugas.com.security.models.request.AuthResponse;
import tugas.com.security.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService {

    private UserDetailService userDetailService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserDetailService userDetailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(AuthRequest authRequest){
        AuthResponse authResponse = new AuthResponse();
        User user = new User();
        user = userRepository.findByUsername(authRequest.getUsername());

        if (user==null){
            throw new RuntimeException(String.format("Username '%s' not found",authRequest.getUsername() ));
        }

        boolean isPasswordMatches  = passwordEncoder.matches(authRequest.getPassword(), user.getPassword());

        if (isPasswordMatches==true){
            List<String> datarequest = userDetailService.loadUserByUsername(authRequest.getUsername()).getAuthorities()
                    .stream()
                    .map(auth -> auth.getAuthority())
                    .collect(Collectors.toList());
            authResponse.setAuthorities(datarequest);
            return authResponse;
        }else {
            throw new UsernameNotFoundException("password tidak ditemukan");
        }
    }
}
