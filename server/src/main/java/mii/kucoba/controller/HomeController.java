/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author haikal
 */
@RestController
public class HomeController {
    
    @GetMapping("/home")
//    @PreAuthorize("hasAuthority('READ_HOME')")
    public String home() {
    return "Ini adalah halaman Rumah";
    }
    
    @GetMapping("/tes_user")
    public String user() {
        return "TES USER";
    }
    
}
