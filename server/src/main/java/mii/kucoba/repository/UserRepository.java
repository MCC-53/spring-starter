/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.repository;

import javax.transaction.Transactional;
import mii.kucoba.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abiyo
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Modifying
    @Query(value = "insert into user(id,username,password) values (:id,:username,:password)", nativeQuery = true)
    @Transactional
    User createAkun(
            @Param("id") Integer id,
            @Param("username") String username,
            @Param("password") String password
            );
    
    User findByUsername(String username);
}
