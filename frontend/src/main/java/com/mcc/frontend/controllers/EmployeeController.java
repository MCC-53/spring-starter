/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.frontend.controllers;

import com.mcc.frontend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HP
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model){
        model.addAttribute("employee", employeeService.getById(id));
        
        return "/employee/get-by-id";
    }
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("employees", employeeService.getAll());
        
        return "/employee/get-all";
    }
}
