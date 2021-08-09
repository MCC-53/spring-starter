/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.repository;

import mii.kucoba.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author haikal
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);
}
