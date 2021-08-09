package mcc53.app.client.clientapp.controllers;

import mcc53.app.client.clientapp.models.Employee;
import mcc53.app.client.clientapp.services.DepartmentService;
import mcc53.app.client.clientapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author firmanzega
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    private EmployeeService employeeService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    
    @GetMapping
    public String getView(Model model){
        model.addAttribute("departments", departmentService.getAll().getPayLoad());
        
        return "employee/view";
    }
    
    @GetMapping("/add")
    public String create(Model model) {
        model.addAttribute("employeeAdd", new Employee());
        return "employee/add-employee";
    }
    
    @PostMapping("/add")
    public String saveCreate(Employee employee){
        employeeService.create(employee);
        return "redirect:/employees";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("employeeUpdate", employeeService.getById(id).getPayLoad());
        return "employee/update-employee";
    }
    
    @PostMapping("/update/{id}")
    public String saveUpdate(@PathVariable("id") Long id, Employee employee) {
        employeeService.update(id, employee);
        return "redirect:/employees";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
