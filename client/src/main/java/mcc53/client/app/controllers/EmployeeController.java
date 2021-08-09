/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;

import mcc53.client.app.models.Employee;
import mcc53.client.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author haikal
 */
@Controller
@RequestMapping("/emp")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
    public String getAllDataEmployee(Model model) {
        model.addAttribute("employees", employeeService.getAllDataEmployee());
        return "employee/index";
    }
    
    @GetMapping("/add")
    public String showForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employee/form";
    }
    
    @PostMapping("/add")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.create(employee);
        return "redirect:/emp";
    }

    @GetMapping("/update/{id}")
    public String updateEmployee(Model model, @PathVariable("id") Integer id) {
        Employee em = employeeService.getEmployeeById(id);
        model.addAttribute("employee", em);
        return "employee/update-employee";
    }
    
    @PostMapping("/update/{id}")
    public String updateDataEmployee(Employee employee, @PathVariable("id") Integer id) {
        employeeService.updateDataEmployee(id, employee);
        return "redirect:/emp";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteDataEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteDataEmployee(id);
        return "redirect:/emp";
    }
    
}
