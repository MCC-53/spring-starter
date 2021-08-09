/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.service.admin;

import mii.kucoba.models.Privilege;
import mii.kucoba.repository.admin.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author haikal
 */
@Service
public class PrivilegeService {
    private PrivilegeRepository privilegeRepository;

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    
    
    public Privilege getByIdPrivilege(Integer id){
        return privilegeRepository.findById(id)
                .orElseThrow( () 
                    -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Role Tidak Ditemukan")
                );
    }
    
    public Privilege createDataPrivilege(Privilege p){
        if(p.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tidak boleh menginputkkan ID");
        }
        return privilegeRepository.save(p);
    }
    
    public Privilege update(Integer id, Privilege p) {
        getByIdPrivilege(id);
        p.setId(id);
        return privilegeRepository.save(p);
    }
    
    public Privilege delete(Integer id) {
        Privilege privilege = getByIdPrivilege(id);
        privilegeRepository.deleteById(id);
        return privilege;
    }
}
