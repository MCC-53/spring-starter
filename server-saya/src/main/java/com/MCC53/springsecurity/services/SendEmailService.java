/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.services;

import com.MCC53.springsecurity.models.SendEmail;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author user
 */
@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    
    public SendEmail sendSimpleMessage(SendEmail sendEmail, Context context) {
        MimeMessage msg = emailSender.createMimeMessage();
        
        try {
            String emailBody = springTemplateEngine.process("email", context);
            MimeMessageHelper message = new MimeMessageHelper(msg,StandardCharsets.UTF_8.name());
            String htmlMsg = sendEmail.getText();
            message.setTo(sendEmail.getTo());
            message.setSubject(sendEmail.getSubject());
            message.setText(emailBody, true);
//            msg.setContent(htmlMsg, "<h3>Hello World!</h3>");
            emailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
            
        }
        return sendEmail;
    }
}

