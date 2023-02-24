package com.pie.planingapispring.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("26kworld@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        //todo Enlever le commentaire si vous voulez l'envoie des email on est limiter a 500 par mois
        //emailSender.send(message);
    }
}