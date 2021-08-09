package com.mii.clinetapp.controllers;

import com.mii.clinetapp.models.Department;
import com.mii.clinetapp.models.Employee;
import com.mii.clinetapp.services.DepartmentService;
import com.mii.clinetapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
//    private DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get-all")
    public @ResponseBody
    Collection<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("employees", employeeService.getAll());
        return "employees/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        model.addAttribute("employee", employeeService.getById(id));
        return "employees/get-by-id";
    }

    @GetMapping("/add")
    public String getForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/create-emp";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.createEmp(employee);
        return "redirect:/employee";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "employees/update";
    }

    @PostMapping("update/{id}")
    public String saveForm(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee){
        employeeService.update(id, employee);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id){
        employeeService.delete(id);
        return "redirect:/employee";
    }




}
