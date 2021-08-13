package mcc53.com.services;

import mcc53.com.models.AuthRequest;
import mcc53.com.models.AuthResponse;
import mcc53.com.models.Privilege;
import mcc53.com.models.User;
import mcc53.com.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public AuthResponse authUserLogin(AuthRequest authRequest){
        AuthResponse authResponse = new AuthResponse();
        List<String> listPrivilege = new ArrayList<>();
        User user = userRepository.findByUsername(authRequest.getUsername());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username anda salah!");
        } boolean passwordCheck = passwordEncoder.matches(authRequest.getPassword(), user.getPassword());

        if (passwordCheck == false) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password anda salah!");
        }
        for (Privilege privilege : user.getRoles().get(0).getPrivileges()) {
            listPrivilege.add(privilege.getPrivilegeName());
        } authResponse.setAuthoritiesResponse(listPrivilege);

        return authResponse;
    }
}
