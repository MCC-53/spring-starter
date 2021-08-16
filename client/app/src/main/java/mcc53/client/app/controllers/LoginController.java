/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;
import mcc53.client.app.models.AuthResponse;
import mcc53.client.app.models.LoginData;
import mcc53.client.app.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author ACER
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @GetMapping
    public String showLoginForm(/*Model model*/) {
        System.out.println("Masuk sini nih proses 1-nya!");
        //model.addAttribute("logindata", new LoginData());
        return "auth/login";
    }
    @PostMapping
    public @ResponseBody AuthResponse /*String*/ create(/*@ModelAttribute("logindata")*/ @RequestBody LoginData loginData) {
        System.out.println("Masuk sini nih proses 2-nya!");
        return loginService.login(loginData);
        //return "SUKSES!";
    }
}