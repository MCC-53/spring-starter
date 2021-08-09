package com.nieceoftimes.clientside.controller;

import com.nieceoftimes.clientside.model.Department;
import com.nieceoftimes.clientside.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentServiceImpl departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String readAllDepartment(Model model) {
        model.addAttribute("departments", departmentService.readAllDepartment());

        return "department/index";
    }




    /** Hit API with Rest Template Implementation */
    @GetMapping("/{id}")
    public String readDepartmentById(@PathVariable String id,
                                     Model model) {
        model.addAttribute("department", departmentService.readDepartmentById(id));

        return "department/department-retrieve";
    }

    @GetMapping("/form")
    public String viewDepartmentCreation(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);

        return "department/department-creation";
    }

    @PostMapping("/form")
    public String createDepartment(Department department) {
        departmentService.createDepartment(department);

        return "redirect:/department";
    }

    @GetMapping("/reform/{id}")
    public String viewDepartmentUpdate(@PathVariable String id,
                                       Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("departmentUpdate", departmentService.readDepartmentById(id).getContent());
        return "department/department-update";
    }

    @PostMapping("/reform/{id}")
    public String updateDepartment(@PathVariable String id,
                                   Department department) {
        departmentService.updateDepartmentById(id, department);

        return "redirect:/department";
    }

    @GetMapping("/remove/{id}")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartmentById(id);

        return "redirect:/department";
    }

}
