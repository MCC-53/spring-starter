/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.controllers;
import com.springboottest.demo.models.AuthRequest;
import com.springboottest.demo.models.AuthResponse;
import com.springboottest.demo.models.RegisterDto;
import com.springboottest.demo.models.Users;
import com.springboottest.demo.services.AuthService;
import com.springboottest.demo.services.RegisterDtoService;
import com.springboottest.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    private UsersService userService;
    private RegisterDtoService registerDtoService;
    private AuthService authService;
    @Autowired
    public UsersController(UsersService userService, RegisterDtoService registerDtoService, AuthService authService) {
        this.userService = userService;
        this.registerDtoService = registerDtoService;
        this.authService = authService;
    }
    @GetMapping("/get-user-by-username")
    @PreAuthorize("hasAuthority('READ_USER_BY_USERNAME')")
    public ResponseEntity<Users> getById(Authentication authentication) {
        return new ResponseEntity(userService.getByUsername(authentication.getName()), HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<Users> create(@RequestBody Users users) {
        return new ResponseEntity(userService.create(users), HttpStatus.OK);
    }
    @PostMapping("/create-register")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<RegisterDto> createRegister(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity(registerDtoService.createRegister(registerDto), HttpStatus.OK);
    }
    @PostMapping("/users-login")
    public ResponseEntity<AuthResponse> authUserLogin(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity(authService.authUserLogin(authRequest), HttpStatus.OK);
    }
    @PutMapping("/update-register/{id}")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public ResponseEntity<RegisterDto> updateRegister(@PathVariable Long id, @RequestBody RegisterDto registerDto) {
        return new ResponseEntity(registerDtoService.update(id, registerDto), HttpStatus.OK);
    }
    @DeleteMapping("/delete-register/{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public ResponseEntity<RegisterDto> deleteRegister(@PathVariable Long id) {
        return new ResponseEntity(registerDtoService.delete(id), HttpStatus.OK);
    }
}