/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.app.client.clientapp.controllers;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import mcc53.app.client.clientapp.models.AuthResponse;
import mcc53.app.client.clientapp.models.Login;
import mcc53.app.client.clientapp.models.ResponseSingle;
import mcc53.app.client.clientapp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author firmanzega
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String GetAll(Model model) {
        model.addAttribute("thismodel", new Login());
        return "login/login";
    }
    
    @PostMapping
    public @ResponseBody ResponseSingle<AuthResponse> getLogin(@RequestBody Login login) {
        return loginService.loginRequest(login);
    }

}
