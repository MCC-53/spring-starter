/*
 * To change this license header, choose License Headers in Department Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;
import java.util.List;
import mcc53.client.app.models.DepartmentData;
import mcc53.client.app.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author ACER
 */
@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/get-all")
    public @ResponseBody List<DepartmentData> getAll() {
        return departmentService.getAll();
    }
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "department/get-all";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        return "department/get-by-id";
    }
    @GetMapping("get-by-id/{id}")
    public @ResponseBody DepartmentData getById(@PathVariable("id") Integer id) {
        return departmentService.getById(id);
    }
    @GetMapping("/add-department")
    public String showFormInput(Model model) {
        model.addAttribute("department", new DepartmentData());
        return "department/input-department-form";
    }
    @GetMapping("/update-department/{id}")
    public String showFormUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        return "department/update-department-form";
    }
    @PostMapping("/save")
    public @ResponseBody DepartmentData create(@RequestBody DepartmentData departmentData) {
        return departmentService.create(departmentData);
    }
    @PutMapping("/update")
    public @ResponseBody DepartmentData update(@RequestBody DepartmentData departmentData) {
        departmentService.update(departmentData);
        return departmentData;
    }
    @DeleteMapping("/delete/{id}")
    public @ResponseBody String delete(@PathVariable("id") Integer id) {
        departmentService.delete(id);
        return "deleted!";
    }
}