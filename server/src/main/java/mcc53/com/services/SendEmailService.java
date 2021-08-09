package mcc53.com.services;

import mcc53.com.models.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender emailSender;

    public SendEmail sendSimpleMessage(SendEmail sendEmail) {
        MimeMessage msg = emailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(msg, StandardCharsets.UTF_8.name());
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
