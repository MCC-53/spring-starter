/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.controllers;
import com.springboottest.demo.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author ACER
 */
@Controller
@RequestMapping("/employees-view")
public class EmployeesViewController {
    private EmployeesService employeesService;
    @Autowired
    public EmployeesViewController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }
    @GetMapping("/{id}")
    public String viewAll(Model model, @PathVariable("id") Long id) {
        model.addAttribute("employees", employeesService.getById(id));
        return "employees_view/index";
    }
}