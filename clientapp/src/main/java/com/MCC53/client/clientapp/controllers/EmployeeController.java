/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.client.clientapp.controllers;

import com.MCC53.client.clientapp.models.Employee;
import com.MCC53.client.clientapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author user
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping()
    public String getAll() {
        return "/employee/index";
    }
    
//    @GetMapping
//    public String getAll(Model model){
//        model.addAttribute("employees", employeeService.getAll());
//        
//       return "/employee/index";
//        
//    }
//    //getbyid
//    @GetMapping("/{id}")
//    public String getByid (@PathVariable ("id") Long id, Model model){
//        model.addAttribute("employee", employeeService.getByid(id));
//        
//        return "/employee/getByid";
//    }
//    @GetMapping("/create") //blm bisa
//    public String createView(){
//        Employee employee = Employee.builder()
////                .firstName(Ayu)
//                .build();
//          employeeService.create(employee);
//          
//          return "/employee/createview";
//    }
}
