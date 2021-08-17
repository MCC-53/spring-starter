/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.repositories;

import com.MCC53.springsecurity.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    
  User findByUsername (String username);
    
    
}
