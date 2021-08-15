/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc3.com.security.services;

import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import mcc3.com.security.models.entities.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author firmanzega
 */
@Service
public class SendEmailService {
    
    @Autowired
    private JavaMailSender emailSender;
    
    public SendEmail sendSimpleMessage(SendEmail sendEmail) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
            helper.setTo(sendEmail.getTo()); 
            helper.setSubject(sendEmail.getSubject());
            helper.setText(sendEmail.getText(), true);
            
            emailSender.send(message);
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
        }
        return sendEmail;
    }
    
}
