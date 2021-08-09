/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import mcc53.com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Xvitas
 */
@Controller
@RequestMapping("/employee-view")
public class EmployeeViewController {
    
    private EmployeeService employeeService;

    @Autowired
    public EmployeeViewController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("employees", employeeService.getAlll());
        return "employee/employee";
    }
    
    @GetMapping("/{id}")
    public String getByIdView(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/employee-by-id";
    }
}
