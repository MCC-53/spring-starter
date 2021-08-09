/*
 * To change this license header, choose License Headers in Department Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author Xvitas
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    
    
//==============================================================================
//    GETALL
    @GetMapping()
    public String getAll() {
        return "/department/view";
    }
//==============================================================================
}



// YANG LAMA, GA KEPAKE LAGI!!!
//    private DepartmentService departmentService;
//
//    @Autowired
//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
////==============================================================================
////    GETALL
//
//    @GetMapping()
//    public String getAll(Model model) {
//        model.addAttribute("datas", departmentService.getAll());
//
//        return "/department/view";
//    }
//
//    @GetMapping("/get-all")
//    public @ResponseBody
//    List<Department> getAll() {
//        return departmentService.getAll();
//    }
////==============================================================================
////    GETBYID
//
//    @GetMapping("{id}")
//    public @ResponseBody
//    Department getById(@PathVariable("id") Integer id) {
//        return departmentService.getById(id);
//    }
////==============================================================================
////    CREATE
//
//    @PostMapping("/create")
//    public @ResponseBody Department create(@RequestBody Department department){
//        return departmentService.create(department);
//    }
////    @GetMapping("/add")
////    public String showForm(Model model) {
////        Department department = new Department();
////        model.addAttribute("department", department);
////        return "department/add-department";
////    }
////
////    @PostMapping("/add")
////    public String postDeparment(@ModelAttribute("department") Department department) {
////        departmentService.createDepartment(department);
////        return "redirect:/department";
////    }
////==============================================================================
////    UPDATE
//
//    @PutMapping("/{id}")
//    public @ResponseBody String update(@PathVariable("id") Integer id,
//            @RequestBody Department department) {
//        return departmentService.update(id, department);
//    }
//    
////    @GetMapping("/update/{id}")
////    public String updateDepartment(Model model, @PathVariable("id") Integer id) {
////        Department p = departmentService.getDepartmentById(id);
////        model.addAttribute("department", p);
////        return "department/update-department";
////    }
////
////    @PostMapping("/update/{id}")
////    public String updateDepartment(Department department,
////            @PathVariable("id") Integer id) {
////        departmentService.updateDepartment(id, department);
////        return "redirect:/department";
////    }
////==============================================================================
////    DELETE
//
//    @DeleteMapping("/{id}")
//    public @ResponseBody String delete(@PathVariable("id") Integer id) {
//        return departmentService.delete(id);
//    }
////==============================================================================