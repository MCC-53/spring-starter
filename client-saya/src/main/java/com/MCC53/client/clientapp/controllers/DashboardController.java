package com.MCC53.client.clientapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
//@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
public class DashboardController {

    @GetMapping
//    @PreAuthorize("hasAuthority ('READ_DATA')")
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "/index";
    }
}