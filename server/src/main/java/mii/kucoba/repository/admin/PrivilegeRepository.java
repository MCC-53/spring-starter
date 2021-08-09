/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.repository.admin;

import mii.kucoba.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author haikal
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer>{
    
}
