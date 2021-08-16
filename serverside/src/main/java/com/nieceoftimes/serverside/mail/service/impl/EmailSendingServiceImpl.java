package com.nieceoftimes.serverside.mail.service.impl;

import com.nieceoftimes.serverside.mail.model.EmailSending;
import com.nieceoftimes.serverside.mail.service.EmailSendingService;
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
public class EmailSendingServiceImpl implements EmailSendingService {
    private JavaMailSender javaMailSender;
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    public EmailSendingServiceImpl(JavaMailSender javaMailSender,
                                   SpringTemplateEngine springTemplateEngine) {

    }

    public EmailSending plainMessageRedirect(EmailSending emailSending,
                                             Context context) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            String emailBody = springTemplateEngine.process("email", context);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                    StandardCharsets.UTF_8.name());

            mimeMessageHelper.setTo(emailSending.getEmailReceiver());
            mimeMessageHelper.setSubject(emailSending.getEmailSubject());
            mimeMessageHelper.setText(emailBody, true);
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
        return emailSending;
    }
}
