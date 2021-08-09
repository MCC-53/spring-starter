/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.repositories;

import java.util.Optional;
import mcc53.com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xvitas
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);
//    Optional<User> findByUsername(String username);
}
