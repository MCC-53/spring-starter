package mcc53.com.controllers;

import mcc53.com.models.SendEmail;
import mcc53.com.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {


    SendEmailService sendEmailService;

    @Autowired
    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }


    @PostMapping
    public SendEmail SendEmail(@RequestBody SendEmail sendEmail) {
        return sendEmailService.sendSimpleMessage(sendEmail);
    }




}
