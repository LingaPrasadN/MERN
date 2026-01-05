package com.t4.app_backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailSender mailSender;

    public void sendOtp(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText(
                "Your OTP is: " + otp + "\n\n" +
                        "This OTP is valid for 5 minutes.\n" +
                        "Do not share it with anyone.");
        mailSender.send(message);
    }

    public void sendInviteEmail(String fromUser, String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Hey! You have a message from a friend");
        message.setText(
                "User with ID " + fromUser + " has invited you to join our platform.\n\n" +
                        "Click the link below to accept the invitation:\n" +
                        "http://randomlink");
        mailSender.send(message);
    }
}
