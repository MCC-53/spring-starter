package com.mcc53.client.app.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @PostMapping
    public @ResponseBody String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/logout";
    }
}
