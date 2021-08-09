/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mcc53.ClientApp.Controllers;

import Mcc53.ClientApp.Models.Employee;
import Mcc53.ClientApp.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author putug
 */

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final String _FOLDER = "Employee";
    private EmployeeService _employeeService;

    @Autowired
    public EmployeeController(EmployeeService _employeeService) {
        this._employeeService = _employeeService;
    }
    
    @GetMapping
    public String showAll(Model model){
        model.addAttribute("employees", _employeeService.getAll());
        return String.format("/%s/%s", _FOLDER, "index");
    }
}
