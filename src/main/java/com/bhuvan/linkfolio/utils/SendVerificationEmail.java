package com.bhuvan.linkfolio.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendVerificationEmail {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String verificationToken) throws MessagingException {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Email Verification for linkFolio 😊😎");
            String verificationLink = "http://localhost:8080/api/v1/users/verify?token=" + verificationToken;
            helper.setText("Please click the following link to verify your email: " + verificationLink, true);
            mailSender.send(message);
            log.info("Email sent successfully to " + to);
    }
}
