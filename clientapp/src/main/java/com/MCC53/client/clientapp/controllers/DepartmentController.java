/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.client.clientapp.controllers;

import com.MCC53.client.clientapp.models.Department;
import com.MCC53.client.clientapp.services.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;
    
//  ============================================================================
    //getAll
     @GetMapping()
    public String getAll() {
        return "/department/view";
    }

    
    
//    @GetMapping
//    public String getAll(Model model){
//        model.addAttribute("department",departmentService.getAll());
//        
//        return "/department/view";  
//    }
//    
//    @GetMapping("/get-all")
//    public @ResponseBody List<Department> getAll(){
//        return departmentService.getAll();
//}
//    @GetMapping("{id}")
//    public @ResponseBody Department getById(@PathVariable("id") Long id){
//        return departmentService.getById(id);
//    }        
//    
//    @GetMapping("/id")
//    public String getById (@PathVariable ("id") Long id, Model model){
//        model.addAttribute("department",departmentService.getById(id));
//        
//        return "/department/get-by-id";
//    }
//  ============================================================================
//
//    //creat form add
//    @GetMapping("/add")
//    public String addForm (Model model){
//        Department department = new Department();
//        model.addAttribute("department", department);
//        
//        return "/department/form-department";
//    }
//    //saveproject
//    @PostMapping ("/add")
//    public String saveDept(@ModelAttribute("department")Department department ){
//        departmentService.createDepartment(department);
//        
//        return "redirect:/department";
//    }
//    
//     @PostMapping("/create")
//    public @ResponseBody Department createDepartment(@RequestBody Department department){
//        return departmentService.createDepartment(department);
//    }
////    ============================================================================
//    
////    @GetMapping("/update/{id}")
////    public String updateForm (Model model,@PathVariable("id") Long id){
////        Department dep = departmentService.getById(id);
////        model.addAttribute("department",dep);
////        
////        return "department/update-dept";
////    }
////    @PostMapping("/update/{id}")
////    public String updateDepartment(Department department, @PathVariable("id")Long id){
////        
////        departmentService.updateDepartment(id, department);
////        
////        return "redirect:/department";
////    }
//    
//   @PutMapping("/{id}")
//    public @ResponseBody String update(@PathVariable("id") Long id, @RequestBody Department department) {
//        return departmentService.updateDepartment(id,department);
//    }
////    =============================================================================
////    @GetMapping("/delete/{id}")
////    public String deleteDepartment (@PathVariable("id") Long id){
////        departmentService.deleteDepartment(id);
////        
////        return "redirect:/department";
////    }
//     @DeleteMapping("/{id}")
//    public @ResponseBody String delete(@PathVariable("id") Long id) {
//        return departmentService.deleteDepartment(id);
//    }
}
