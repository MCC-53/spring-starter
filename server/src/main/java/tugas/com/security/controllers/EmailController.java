/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tugas.com.security.models.EmailToSend;
import tugas.com.security.services.EmailService;

/**
 *
 * @author putug
 */
@RestController
@RequestMapping("/email")
public class EmailController {
    
    
    EmailService sendEmailService;

    @Autowired
    public EmailController(EmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }
    
    
//    @GetMapping
//    public ResponseEntity SendEmail(@RequestBody EmailToSend email) {
//        sendEmailService.sendSimpleMessage(email);
//        return new ResponseEntity(HttpStatus.OK);
//    }   
}
