/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;

import java.util.List;
import mcc53.client.app.models.Department;
import mcc53.client.app.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author haikal
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
    public String getAllDepartment(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartment());
        return ("department/view");
    }
    
    @GetMapping("/get-all")
    public @ResponseBody List<Department> getAll() {
        return departmentService.getAllDepartment();
    }
    
    @GetMapping("{id}")
    public @ResponseBody Department getById(@PathVariable("id") Integer id){
        return departmentService.getDepartmentById(id);
    }
    
    @GetMapping("/add")
    public String showForm(Model model){
        Department department = new Department();
        model.addAttribute("department",department);
        return "department/form-department";
    }

    @PostMapping("/add")
    public String saveDeparment(@ModelAttribute("department") Department department){
        departmentService.create(department);
        return "redirect:/department";
    }
    
    @GetMapping("/update/{id}")
    public String UpdateDepartment(Model model, @PathVariable("id") Integer id) {
        Department dep = departmentService.getDepartmentById(id);
        model.addAttribute("department", dep);
        return "department/update-department";
    }
    
    @PostMapping("/update/{id}")
    public String updateDataDepartment(Department department, @PathVariable("id") Integer id) {
        departmentService.updateDataDepartment(id, department);
        return "redirect:/department";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteDataDepartment(@PathVariable("id") Integer id) {
        departmentService.deleteDataDepartment(id);
        return "redirect:/department";
    }
}
