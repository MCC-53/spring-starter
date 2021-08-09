/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author haikal
 */
@Controller
@RequestMapping("/")
public class DashboardController {

    public DashboardController() {
    }
    
    @GetMapping
    public String indexWeb() {
        return "/index";
    }
    
}
