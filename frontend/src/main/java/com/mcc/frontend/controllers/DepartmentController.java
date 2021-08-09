/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.frontend.controllers;

import com.mcc.frontend.models.Department;
import com.mcc.frontend.services.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HP
 */

@Controller
@RequestMapping("/department")
public class DepartmentController {
    
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("departments", departmentService.getAll());
        return "/department/index";
    }
    
    @GetMapping("/get-all")
    public @ResponseBody List<Department> getAllDepartment(){
        return departmentService.getAll();
    }
    
    @GetMapping("/{id}")
    public @ResponseBody Department getById(@PathVariable("id") Integer id){
        return departmentService.getById(id);
    }

    @PostMapping("/save")
    public @ResponseBody Department saveDepartment(@RequestBody Department department){
        return departmentService.create(department);
    }

    @PutMapping("/{id}")
    public @ResponseBody String update(@PathVariable("id") Integer id, @RequestBody Department department) {
        return departmentService.update(id,department);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable("id") Integer id) {
        return departmentService.delete(id);
    }
    
//    @GetMapping("/add")
//    public String createFormDepartment(Model model){
//        Department department = new Department();
//        model.addAttribute("department", department);
//        
//        return "/department/add-department";
//    }
//    
//    @PostMapping("/add")
//    public String createDepartment(Department department){
//        departmentService.create(department);
//        
//        return "redirect:/department";
//    }
//    
//    @GetMapping("/update/{id}")
//    public String updateFormDepartment(@PathVariable("id") Integer id, Model model){
//        Department department = departmentService.getById(id);
//        model.addAttribute("department", department);
//        return "department/update-department";
//    }
//    
//    @PostMapping("/update/{id}")
//    public String updateDepartment(@PathVariable("id") Integer id, Department department){
//        departmentService.update(id, department);
//        return "redirect:/department";
//    }
//    
//    @GetMapping("/delete/{id}")
//    public String deleteDepartment(@PathVariable("id") Integer id){
//        departmentService.delete(id);
//        return "redirect:/department";
//    }
}
