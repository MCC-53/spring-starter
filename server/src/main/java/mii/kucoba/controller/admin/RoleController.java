/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.controller.admin;

import mii.kucoba.config.ResponseMessage;
import mii.kucoba.models.Role;
import mii.kucoba.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/role")
public class RoleController {
    
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
            
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Role> createDataRole(@RequestBody Role r, Authentication auth){
        System.out.println(auth.getAuthorities().toString());
        return new ResponseEntity(new ResponseMessage<Role>
            (roleService.createDataRole(r), "Success"), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Role> update(@PathVariable("id") Integer id,
            @RequestBody Role r) {
        return new ResponseEntity(roleService.update(id, r), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Role> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity(roleService.delete(id), HttpStatus.OK);
    }
    
}
