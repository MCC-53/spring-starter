/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.service;

import java.nio.charset.StandardCharsets;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import mii.kucoba.models.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author haikal
 */
@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender emailSender;

    public SendEmail sendSimpleMessage(SendEmail sendEmail) {
        MimeMessage msg = emailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(msg,StandardCharsets.UTF_8.name());
            String htmlMsg = sendEmail.getText();
            message.setTo(sendEmail.getTo());
            message.setSubject(sendEmail.getSubject());
            message.setText(htmlMsg, true);
//            msg.setContent(htmlMsg, "<h3>Hello World!</h3>");
            emailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
            
        }
        return sendEmail;
    }
}
