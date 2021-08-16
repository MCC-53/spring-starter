/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author ACER
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @GetMapping
    public String showLayout(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "index";
    }
}