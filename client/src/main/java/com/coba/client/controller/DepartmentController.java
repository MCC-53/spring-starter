package com.coba.client.controller;
import com.coba.client.models.Department;
import com.coba.client.models.Employee;
import com.coba.client.models.ResponseMessage;
import com.coba.client.models.ResponseSingle;
import com.coba.client.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("department",departmentService.getAlll());
        return "department/view";
    }

//    @GetMapping("/{id}")
//    public String getById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("department", departmentService.getById(id));
//        return "/department/view-id";
//    }


    @GetMapping("/add")
    public String showForm(Model model){
        Department department = new Department();
        model.addAttribute("department",department);
        return "department/form-department";
    }

    @GetMapping("/formUpdate")
    public String showFormUpdate(@RequestParam("departmentId")Long departmentId,
                                 Model model){
        Department departmentx = new Department();
        model.addAttribute(departmentx);
        ResponseSingle<Department> department = departmentService.getById(departmentId);
        System.out.println(department);
        model.addAttribute("department",department);
        return "department/update-form";
    }
    List<Department> dept = new ArrayList<>();
//    @PostMapping("/save")
//    public ResponseEntity<Department> saveDeparment(@RequestBody Department department){
//        dept.add(department);
//        System.out.println(department);
//        Object temp = departmentService.create(department);
//        return new ResponseEntity(temp, HttpStatus.OK);
//    }

//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id")Long id){
//        departmentService.del(id);
//        return "redirect:/department";
//    }

    //Ajax Controller
    @GetMapping("/get-all")
    public @ResponseBody List<Department> getAll() {
        return departmentService.getAll_Ajax().getData();
    }

    @GetMapping("/{id}")
    public @ResponseBody Department getById(@PathVariable("id") Long id) {
        return departmentService.getById(id).getData();
    }

    @GetMapping("/delete/{id}")
    public @ResponseBody Department cDel_ajax(@PathVariable("id")Long id){
        return  departmentService.del_ajax(id);
    }

    @PostMapping("/save")
    public @ResponseBody Department saveDeparment(@RequestBody Department department){
        return departmentService.create(department);
    }

    @PutMapping("/{id}")
    public @ResponseBody Department update(@PathVariable("id") Long id, @RequestBody Department department) {
        return departmentService.create(department);
    }
}
