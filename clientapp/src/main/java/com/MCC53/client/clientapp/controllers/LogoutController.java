/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.client.clientapp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
    
     @PostMapping("/logout")
    public @ResponseBody String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/logout";
    }

}
    

