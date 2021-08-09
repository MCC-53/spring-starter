/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.controller.admin;

import mii.kucoba.config.ResponseMessage;
import mii.kucoba.models.Privilege;
import mii.kucoba.service.admin.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author haikal
 */
@Controller
@RequestMapping("/privilege")
public class PrivilegeController {
    
    private PrivilegeService privilegeService;

    @Autowired
    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }
            
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Privilege> createDataRole(@RequestBody Privilege p){
        return new ResponseEntity(new ResponseMessage<Privilege>
            (privilegeService.createDataPrivilege(p), "Success"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Privilege> update(@PathVariable("id") Integer id,
            @RequestBody Privilege p) {
        return new ResponseEntity(privilegeService.update(id, p), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Privilege> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity(privilegeService.delete(id), HttpStatus.OK);
    }
    
}
