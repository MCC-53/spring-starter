package mcc53.app.client.clientapp.controllers;

import mcc53.app.client.clientapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author firmanzega
 */
@Controller
@RequestMapping("/departments")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;
//    
    @GetMapping
    public String getView(Model model){
        model.addAttribute("departments", departmentService.getAll());
        return "department/view";
    }
    
}
