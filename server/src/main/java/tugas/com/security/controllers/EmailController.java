package tugas.com.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tugas.com.security.models.SendEmail;
import tugas.com.security.services.SendEmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    SendEmailService sendEmailService;

    @Autowired
    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

//    @PostMapping
//    public SendEmail sendEmail(@RequestBody SendEmail sendEmail){
//        return sendEmailService.sendSimpleMessage(sendEmail);
//    }

}
