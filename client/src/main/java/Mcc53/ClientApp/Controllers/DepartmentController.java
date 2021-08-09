/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mcc53.ClientApp.Controllers;

import Mcc53.ClientApp.Models.Department;
import Mcc53.ClientApp.Services.DepartmentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author putug
 */
@Controller
@RequestMapping("department")
public class DepartmentController {

    private final String _CONTROLLER = "department";
    private final String _FOLDER = "Department";
    private DepartmentService _departmentService;

    public DepartmentController(DepartmentService departmentService) {
        _departmentService = departmentService;
    }

    @GetMapping("create")
    public String departmentCreateForm(Model model) {
        Department temp = new Department();
        model.addAttribute("model", temp);
        return String.format("/%s/%s", _FOLDER, "createDepartment");
    }

    @PostMapping("create")
    public String departmentCreateForm(@ModelAttribute Department requestCreateDepartment) {
        Department temp = _departmentService.create(requestCreateDepartment);
        String redirectUrl = String.format("%s/%d", _CONTROLLER, temp.getId());
        return "redirect:/" + redirectUrl;
    }

    @GetMapping("{id}")
    public String departmentDetail(Model model, @PathVariable("id") int id) {
        model.addAttribute("department", _departmentService.getById(id));
        return String.format("/%s/%s", _FOLDER, "detailDepartment");
    }

    @GetMapping
    public String showAll(Model model) {
        List<Department> departments = _departmentService.getAll();
//        List<Department> departments = new ArrayList();
//        departments.add(new Department(1, "IT"));
        model.addAttribute("departments", departments);
        return String.format("/%s/%s", _FOLDER, "index");
    }

    @GetMapping("update/{id}")
    public String departmentUpdateForm(@PathVariable("id") int id, Model model) {
        Department temp = _departmentService.getById(id);
        model.addAttribute("department", temp);
        return String.format("/%s/%s", _FOLDER, "updateDepartment");
    }

    @PostMapping("update")
    public String departmentUpdateForm(@ModelAttribute Department requestUpdateDepartment) {
        Department temp = _departmentService.updateById(requestUpdateDepartment);
        String redirectUrl = String.format("%s/%d", _CONTROLLER, temp.getId());
        return "redirect:/" + redirectUrl;
    }

    @PostMapping("delete")
    public String departmentDelete(int id) {
        Department temp = _departmentService.deleteById(id);
        String redirectUrl = String.format("%s", _CONTROLLER);
        return "redirect:/" + redirectUrl;
    }
}
