/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.controller;

import mii.kucoba.models.SendEmail;
import mii.kucoba.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author haikal
 */
@RestController
@RequestMapping("/email")
public class EmailController {
    
    
    SendEmailService sendEmailService;

    @Autowired
    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }
    
    
//    @PostMapping
//    public SendEmail SendEmail(@RequestBody SendEmail sendEmail) {
//        return sendEmailService.sendSimpleMessage(sendEmail);
//    }   
    
    
    
    
}
