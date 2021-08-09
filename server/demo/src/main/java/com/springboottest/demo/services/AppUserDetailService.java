/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.AppUserDetail;
import com.springboottest.demo.models.Users;
import com.springboottest.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 *
 * @author ACER
 */
@Service
public class AppUserDetailService implements UserDetailsService {
    UsersRepository usersRepository;
    @Autowired
    public AppUserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        Users users = usersRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("Username tidak ditemukan!");
        } return new AppUserDetail(users);
    }
}