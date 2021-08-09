/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.repositories;

import mcc53.com.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Xvitas
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
