package mcc53.app.client.clientapp.controllers;

import mcc53.app.client.clientapp.services.UserService;
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
@RequestMapping("/users")
public class UserController {
    
    
    private UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("users", userService.getAll().getPayLoad());
        
        return "user/view";
    }
}
