/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.service.admin;

import mii.kucoba.models.Role;
import mii.kucoba.repository.admin.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author haikal
 */
@Service
public class RoleService {
    
     private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    public Role getByIdRole(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow( () 
                    -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Role Tidak Ditemukan")
                );
    }
    
    public Role createDataRole(Role role){
        if(role.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tidak boleh menginputkkan ID");
        }
        return roleRepository.save(role);
    }
    
    public Role update(Integer id, Role role) {
        getByIdRole(id);
        role.setId(id);
        return roleRepository.save(role);
    }
    
    public Role delete(Integer id) {
        Role role = getByIdRole(id);
        roleRepository.deleteById(id);
        return role;
    }
    
}
