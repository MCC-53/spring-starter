/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.repositories;
import com.springboottest.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author ACER
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}