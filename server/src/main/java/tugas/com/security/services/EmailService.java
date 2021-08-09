/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.com.security.services;

import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import tugas.com.security.models.EmailToSend;

/**
 *
 * @author putug
 */
@Service
public class EmailService{

    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendSimpleMessage(EmailToSend email, Context context) {
        MimeMessage msg = emailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(msg,StandardCharsets.UTF_8.name());
            String emailBody = templateEngine.process(email.getText(), context);
            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(emailBody, true);
            emailSender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
