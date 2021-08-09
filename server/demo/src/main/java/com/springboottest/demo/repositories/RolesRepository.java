/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.repositories;
import com.springboottest.demo.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author ACER
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM userrole WHERE user_id = ?1", nativeQuery = true)
    void deleteUserId(Long id);
}