/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.SendEmail;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
/**
 *
 * @author ACER
 */
@Service
public class SendEmailService {
    private JavaMailSender javaMailSender;
    private SpringTemplateEngine springTemplateEngine;
    @Autowired
    public SendEmailService(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    } public SendEmail sendSimpleMessage(SendEmail sendEmail, Context context) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setSubject(sendEmail.getSubject());
            mimeMessageHelper.setText(springTemplateEngine.process("email", context), true);
            mimeMessageHelper.setTo(sendEmail.getTo());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } return sendEmail;
    }
}