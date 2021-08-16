package mcc53.com.services;

import mcc53.com.models.auth.AppUser;
import mcc53.com.models.dto.LoginRequest;
import mcc53.com.models.dto.LoginResponse;
import mcc53.com.repositories.AppUserRepository;
import mcc53.com.security.BcryptEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginService {
    private BcryptEncode bcryptEncode;
    private AppUserRepository appUserRepository;
    private AppUserService appUserService;

    @Autowired
    public LoginService(BcryptEncode bcryptEncode, AppUserRepository appUserRepository, AppUserService appUserService) {
        this.bcryptEncode = bcryptEncode;
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    public LoginResponse login(LoginRequest request) {
        LoginResponse authorities = new LoginResponse();
        Optional<AppUser> data = appUserRepository.findByUsername(request.getUsername());
        data.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Username not found!"));
        boolean isPasswordMatches = bcryptEncode.bcryptEncoder().matches(
                request.getPassword(), data.get().getPassword());
        if (!isPasswordMatches) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password incorrect!");
        }

        List<String> requestData = appUserService.loadUserByUsername(request.getUsername()).getAuthorities()
                .stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
        authorities.setAuthorities(requestData);
        return authorities;
    }
}
