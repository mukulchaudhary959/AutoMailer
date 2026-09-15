package com.mukul.automailer.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    private final JavaMailSender mailSender;
    private final String from;

    public MailService(JavaMailSender mailSender, @Value("${app.mail.from}") String from) {
        this.mailSender = mailSender;
        this.from = from;
    }

    public SendMailResponse send(SendMailRequest request) {
        List<String> recipients = request.recipients().stream().distinct().toList();

        for (String recipient : recipients) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(recipient);
            message.setSubject(request.subject().trim());
            message.setText(request.body().trim());
            mailSender.send(message);
        }

        return new SendMailResponse("Email sent successfully", recipients.size(), recipients);
    }
}
