package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {
    private final JavaMailSender mailSender;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

public void sendEmail(String to, String subject, String body) {
    try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        logger.info("Email sent successfully to: " + to);
    } catch (Exception e) {
        logger.error("Failed to send email to: " + to, e);
    }
}

}
