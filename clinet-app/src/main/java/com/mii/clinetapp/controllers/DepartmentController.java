package com.mii.clinetapp.controllers;

import com.mii.clinetapp.models.Department;
import com.mii.clinetapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // get all using thymeleaf
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "departments/index";
    }

    //Store data using @Responbody to get using ajax
    @GetMapping("/get-all")
    public @ResponseBody
    Collection<Department> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Department getById(@PathVariable("id") Long id) {
        return departmentService.getById(id);
    }

    @PostMapping("/save")
    public @ResponseBody
    Department saveDepartment(@RequestBody Department department) {
        return departmentService.createDept(department);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    String update(@PathVariable("id") Long id, @RequestBody Department department) {
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String delete(@PathVariable("id") Long id) {
        return departmentService.delete(id);
    }

}

//    @GetMapping("/{id}")
//    public String getById(@PathVariable("id") Long id, Model model){
//        model.addAttribute("department", departmentService.getById(id));
//        return "departments/detail-department";
//    }
//
//    @GetMapping("/form-department")
//    public String getForm(Model model){
//        Department department = new Department();
//        model.addAttribute("department",department);
//        return "departments/create-dept";
//    }
//
//    @PostMapping("/save")
//    public String saveDepartment(@ModelAttribute("department") Department department){
//        departmentService.createDept(department);
//        return "redirect:/department";
//    }
//
//    @PutMapping("/{id}")
//    public @ResponseBody Department update(@PathVariable("id") Long id, @RequestBody Department department){
//        return departmentService.update(id, department);
//    }

    // Create Using Builder

//    @GetMapping("/create")
//    public String createViewDept() {
//
//        Department department = Department.builder()
//                .name("KM")
//                .build();
//
//        departmentService.createDept(department);
//
//        return "home/departments/create";
//    }

