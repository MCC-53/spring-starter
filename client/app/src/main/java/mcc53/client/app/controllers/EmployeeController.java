/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;
import java.util.List;
import mcc53.client.app.models.EmployeeData;
import mcc53.client.app.models.EmployeeDto;
import mcc53.client.app.models.ProjectData;
import mcc53.client.app.services.DepartmentService;
import mcc53.client.app.services.EmployeeService;
import mcc53.client.app.services.ProjectService;
import mcc53.client.app.services.RoleService;
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
 * @author ACER
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private ProjectService projectService;
    private RoleService roleService;
    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, ProjectService projectService, RoleService roleService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.projectService = projectService;
        this.roleService = roleService;
    }
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("projects", projectService.getAll());
        model.addAttribute("roles", roleService.getAll());
        return "employee/get-all";
    }
    @GetMapping("/get-all")
    public @ResponseBody List<EmployeeData<ProjectData>> getAll() {
        return employeeService.getAll();
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/get-by-id";
    }
    @GetMapping("/add-employee")
    public String showFormInput(Model model) {
        EmployeeDto employeeDto = new EmployeeDto();
        model.addAttribute("employeedto", employeeDto);
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("projects", projectService.getAll());
        model.addAttribute("roles", roleService.getAll());
        return "employee/input-employee-form";
    }
    @GetMapping("/update-employee/{id}")
    public String showFormUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/update-employee-form";
    }
    @PostMapping("/save-employee")
    public String create(@ModelAttribute("employeedto") EmployeeDto employeeDto) {
        employeeService.create(employeeDto);
        return "redirect:/employees";
    }
    @PostMapping("/update-employee")
    public String update(@ModelAttribute("employee") EmployeeData<ProjectData> employeeData) {
        employeeService.update(employeeData);
        return "redirect:/employees";
    }
    @GetMapping("/delete-employee/{id}")
    public String delete(@PathVariable("id") Integer id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}