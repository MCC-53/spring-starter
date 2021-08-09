package mcc3.com.security.controllers;

import java.util.List;
import mcc3.com.security.models.entities.User;
import mcc3.com.security.models.request.AuthResponse;
import mcc3.com.security.models.request.LoginRequest;
import mcc3.com.security.models.request.RegisterData;
import mcc3.com.security.models.request.ResponseData;
import mcc3.com.security.services.UserService;
import mcc3.com.security.services.AuthService;
import mcc3.com.security.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author firmanzega
 */

@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;
    private RegisterService registerService;
    private AuthService authService;
    
    @Autowired
    public UserController(UserService userService, RegisterService registerService, AuthService authService) {
        this.userService = userService;
        this.registerService = registerService;
        this.authService = authService;
    }
    
    @GetMapping
    public ResponseEntity<ResponseData<List<User>>> getAll() {
        ResponseData<List<User>> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get All Users");
        responseData.setPayLoad(userService.getAll());
        return ResponseEntity.ok(responseData);
    }
    
    @PostMapping("/user-role/")//OK
    public ResponseEntity<ResponseData<User>> createUserRole( 
            @RequestParam("userId") Long userId, @RequestParam("roleId") Integer roleId) {
        ResponseData<User> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setMessage("Table user_role has been added");
        userService.createUserRole(userId, roleId);
        return ResponseEntity.ok(responseData);
    }
}
