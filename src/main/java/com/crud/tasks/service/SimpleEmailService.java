package com.crud.tasks.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final String receiverEmail, final String subject, final String message) {

        LOGGER.info("Starting email preoaration...");

        try {

            SimpleMailMessage mailMessage = createMailMessage(receiverEmail, subject, message);
            javaMailSender.send(mailMessage);

            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final String receiverEmail, final String subject, final String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        return  mailMessage;
    }
}
