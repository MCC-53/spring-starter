/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.Users;
import com.springboottest.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
/**
 *
 * @author ACER
 */
@Service
public class UsersService {
    private UsersRepository usersRepository;
    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    } public Users getByUsername(String username) {
        return usersRepository.findByUsername(username);
    } public Users create(Users users) {
        if (users.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data user sudah ada!");
        } return usersRepository.save(users);
    }
}