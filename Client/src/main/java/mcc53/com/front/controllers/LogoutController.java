/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Xvitas
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

//==============================================================================
    @PostMapping
    public @ResponseBody
    String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login/loginPage";
    }
//==============================================================================
}
