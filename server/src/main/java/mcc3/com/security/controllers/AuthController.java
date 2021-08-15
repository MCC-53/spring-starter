package mcc3.com.security.controllers;
// OK
import mcc3.com.security.models.entities.User;
import mcc3.com.security.models.request.AuthResponse;
import mcc3.com.security.models.request.LoginRequest;
import mcc3.com.security.models.request.RegisterData;
import mcc3.com.security.models.request.ResponseData;
import mcc3.com.security.services.AuthService;
import mcc3.com.security.services.RegisterService;
import mcc3.com.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author firmanzega
 */

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private AuthService authService;
    private RegisterService registerService;
    private UserService userService;

    @Autowired
    public AuthController(AuthService authService, RegisterService registerService, UserService userService) {
        this.authService = authService;
        this.registerService = registerService;
        this.userService = userService;
    }
    
    @PostMapping("/login")//OK
    public ResponseEntity<ResponseData<AuthResponse>> login(
            @RequestBody LoginRequest request) {
        System.out.println(request);
        ResponseData<AuthResponse> response = new ResponseData<>();
        response.setMessage("Login success!");
        response.setPayLoad(authService.login(request));
        response.setStatus(true);
        return ResponseEntity.ok(response);
    }
    
//    @PostMapping("/go_login")
//    public ResponseEntity<UserDetails> login2(@RequestBody String username){
//        
//        return new ResponseEntity(userService.loadUserByUsername(username), HttpStatus.OK);
//    }
    
    @PostMapping("/register")//OK
    public ResponseEntity<ResponseData<RegisterData>> register(
            @RequestBody RegisterData registerData){
        ResponseData<RegisterData> response = new ResponseData<>();
        System.out.println(registerData);
        response.setMessage("Register success!");
        response.setPayLoad(registerService.register(registerData));
        response.setStatus(true);
        return ResponseEntity.ok(response);
    }
}
