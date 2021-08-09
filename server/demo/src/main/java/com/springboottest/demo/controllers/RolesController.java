/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.controllers;
import com.springboottest.demo.models.Roles;
import com.springboottest.demo.services.RolesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/roles")
public class RolesController {
    private RolesService rolesService;
    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }
    @GetMapping
    //@PreAuthorize("hasAuthority('READ_ALL_ROLES')")
    public ResponseEntity<List<Roles>> getAll() {
        return new ResponseEntity(rolesService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('READ_ROLE_BY_ID')")
    public ResponseEntity<Roles> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(rolesService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE_ROLE')")
    public ResponseEntity<Roles> create(@RequestBody Roles roles) {
        return new ResponseEntity(rolesService.create(roles), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public ResponseEntity<Roles> update(@PathVariable("id") Long id, @RequestBody Roles roles) {
        return new ResponseEntity(rolesService.update(id, roles), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('DELETE_ROLE')")
    public ResponseEntity<Roles> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(rolesService.delete(id), HttpStatus.OK);
    }
}