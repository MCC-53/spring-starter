package mcc53.app.client.clientapp.controllers;

import mcc53.app.client.clientapp.models.Employee;
import mcc53.app.client.clientapp.models.Register;
import mcc53.app.client.clientapp.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author firmanzega
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    
    RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public String create(Model model) {
        model.addAttribute("registerAdd", new Register());
        return "auth/register";
    }
    
    @PostMapping
    public String saveCreate(Register register) {
        registerService.create(register);
        return "redirect:/employees";
    }
}
