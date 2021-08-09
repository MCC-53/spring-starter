/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.Roles;
import com.springboottest.demo.repositories.RolesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
/**
 *
 * @author ACER
 */
@Service
public class RolesService {
    private RolesRepository rolesRepository;
    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    } public List<Roles> getAll() {
        return rolesRepository.findAll();
    } public Roles getById(Long id) {
        return rolesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data role tidak ditemukan!"));
    } public Roles create(Roles roles) {
        if (roles.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data employee sudah ada!");
        } return rolesRepository.save(roles);
    } public Roles update(Long id, Roles roles) {
        roles.setId(getById(id).getId());
        return rolesRepository.save(roles);
    } public Roles delete(Long id) {
        Roles roles = getById(id);
        rolesRepository.deleteById(id);
        return roles;
    }
}