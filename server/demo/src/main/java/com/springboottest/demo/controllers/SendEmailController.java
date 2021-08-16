/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.controllers;
import com.springboottest.demo.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/send-email")
public class SendEmailController {
    private SendEmailService sendEmailService;
    @Autowired
    public SendEmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }
}