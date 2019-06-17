package com.licenta.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    private static JavaMailSender javaMailSender;


    public EmailServiceImpl(){
        javaMailSender = getJavaMailSender();
    }

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);
        mailSender.setUsername("6dc45942694ed6");
        mailSender.setPassword("a8424332b1b0cd");

        return mailSender;
    }

    public void sendMail(String to, String subject, String messageText){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("5d962f75e8-3d1043@inbox.mailtrap.io");
        message.setSubject(subject);
        message.setText(messageText);
        javaMailSender.send(message);
    }
}
