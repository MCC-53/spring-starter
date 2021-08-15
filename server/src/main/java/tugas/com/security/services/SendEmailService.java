package tugas.com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import tugas.com.security.models.SendEmail;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public SendEmail sendSimpleMessage(SendEmail sendEmail, Context context){
        MimeMessage message = emailSender.createMimeMessage();

        try {
            String emailBody = springTemplateEngine.process("email", context);
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
            String htmlMessage = sendEmail.getText();
            messageHelper.setTo(sendEmail.getTo());
            messageHelper.setSubject(sendEmail.getSubject());
            messageHelper.setText(emailBody, true);
            emailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sendEmail;
    }
}
