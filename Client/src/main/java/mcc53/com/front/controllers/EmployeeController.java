/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.controllers;

import java.util.List;
import mcc53.com.front.models.Employee;
import mcc53.com.front.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Xvitas
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
//==============================================================================
//    GETALL

    @GetMapping()
    public String getAll() {
        return "/employee/view";
    }

    @GetMapping("/get-all")
    public @ResponseBody
    List<Employee> getAllService(Employee employee) {
        return employeeService.getAll();
    }
//==============================================================================
//    GETBYID

//    @GetMapping("/get-by-id/{id}")
//    public @ResponseBody
//    Employee getByIdService(@PathVariable("id") Integer id) {
//        return employeeService.getById(id);
//    }
//==============================================================================
//    CREATE

//    @PostMapping("/create")
//    public @ResponseBody
//    Employee create(@RequestBody Employee employee) {
//        return employeeService.create(employee);
//    }
//==============================================================================
//    DELETE

//    @DeleteMapping("/delete/{id}")
//    public @ResponseBody
////            String deleteService(@PathVariable("id") Integer id) {
//    String deleteService(@PathVariable("id") Integer id) {
//        return employeeService.delete(id);
//    }
//==============================================================================
}

// YANG LAMA, GA KEPAKE LAGI!
//    private EmployeeService employeeService;
//
//    @Autowired
//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
//    
//    
////============================================================================
////    GETALL
//
//    @GetMapping
//    public String getAll(Model model) {
//        model.addAttribute("employeeAja", employeeService.getAll());
//
//        return "employee/view";
//    }
////============================================================================
////    CREATE
//
//    @GetMapping("/add")
//    public String showForm(Model model) {
//        Employee employee = new Employee();
//        model.addAttribute("employee", employee);
//        return "employee/add-employee";
//    }
//
//    @PostMapping("/add")
//    public String postEmployee(@ModelAttribute("employee") Employee employee) {
//        employeeService.createEmployee(employee);
//        return "redirect:/employee";
//    }
////============================================================================
////    UPDATE
//
//    @GetMapping("/update/{id}")
//    public String updateEmployee(Model model, @PathVariable("id") Integer id) {
//        Employee p = employeeService.getEmployeeById(id);
//        model.addAttribute("employee", p);
//        return "employee/update-employee";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateEmployee(Employee employee,
//            @PathVariable("id") Integer id) {
//        employeeService.updateEmployee(id, employee);
//        return "redirect:/employee";
//    }
////============================================================================
//////    DELETE
////
////    @GetMapping("/delete/{id}")
////    public String postEmployee(@PathVariable("id") Integer id) {
////        employeeService.deleteEmployee(id);
////        return "redirect:/employee";
////    }
//    
//
////    DELETE
//
//    @DeleteMapping("/{id}")
//    public @ResponseBody String delete(@PathVariable("id") Integer id) {
//        return employeeService.delete(id);
//    }
////============================================================================
