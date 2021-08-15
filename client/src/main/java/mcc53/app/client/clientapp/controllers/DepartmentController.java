package mcc53.app.client.clientapp.controllers;

import mcc53.app.client.clientapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
//@PreAuthorize("hasRole('EMPLOYEE')")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;
//    
    @GetMapping
    public String getView(){
        
        return "/pages/department";
    }
    
}
