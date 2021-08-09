/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.com.frontend.controllers;
import client.com.frontend.models.Employee;
import client.com.frontend.models.Department;
import client.com.frontend.services.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author aceng
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping()
    public String getAll() {
        return "/department/index";
    }
    
//    @Autowired
//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
//
//    @GetMapping
//    public String getAll(Model model){
//        model.addAttribute("dep",departmentService.getAll());
//        return "department/index";
//    }
//    
//    @GetMapping("/get-all")
//    public @ResponseBody List<Department> getAll() {
//        return departmentService.getAll();
//    }
//
//    @GetMapping("{id}")
//    public @ResponseBody Department getById(@PathVariable("id") Long id){
//        return departmentService.getById(id);
//    }
//
//    @PostMapping("/create")
//    public @ResponseBody Department createDepartment(@RequestBody Department department){
//        return departmentService.create(department);
//    }
//
//    @PutMapping("/{id}")
//    public @ResponseBody String update(@PathVariable("id") Long id, @RequestBody Department department) {
//        return departmentService.update(department, id);
//    }
//
//    @DeleteMapping("/{id}")
//    public @ResponseBody String delete(@PathVariable("id") Long id) {
//        return departmentService.delete(id);
//    }

//    @GetMapping("/add")
//    public String showForm(Model model){
//        Department department = new Department();
//        model.addAttribute("department",department);
//        return "department/form-department";
//    }
//
//    @PostMapping("/save")
//    public String saveDeparment(@ModelAttribute("department") Department department){
//        departmentService.create(department);
//        return "redirect:/department";
//    }
}
