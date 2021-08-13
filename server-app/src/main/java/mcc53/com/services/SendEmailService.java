package mcc53.com.services;

import mcc53.com.models.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

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
            MimeMessageHelper message = new MimeMessageHelper(msg, StandardCharsets.UTF_8.name());
            String htmlMsg = sendEmail.getText();
            message.setTo(sendEmail.getTo());
            message.setSubject(sendEmail.getSubject());
            message.setText(emailBody, true);
            emailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return sendEmail;
    }
}
