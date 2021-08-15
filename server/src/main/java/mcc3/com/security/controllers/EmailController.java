/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc3.com.security.controllers;

import mcc3.com.security.models.entities.SendEmail;
import mcc3.com.security.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author firmanzega
 */
@RestController
@RequestMapping("/email")
public class EmailController {
    
    
    private SendEmailService sendEmailService;

    @Autowired
    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }
    
    @PostMapping
    public SendEmail sendEmail(@RequestBody SendEmail sendEmail){
        System.out.println(sendEmail.toString());
        return sendEmailService.sendSimpleMessage(sendEmail);
    }
    
    
}
