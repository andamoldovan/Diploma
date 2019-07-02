package com.licenta.project.config;

import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

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

    public void sendMimeMail(List<ArticleDTO> articleList, String userEmail) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        String htmlMsg = "<body>";
        htmlMsg += "<h1 style=\" margin-bottom: 3%\"> Your News Updates for today!</h1> \n";
        for(ArticleDTO article: articleList) {
            htmlMsg += getArticleHtml(article) + "\n";
        }
        htmlMsg += "</body>";

        mimeMessage.setContent(htmlMsg, "text/html");
        helper.setTo(userEmail);
        helper.setSubject("Daily News update");
        helper.setFrom("5d962f75e8-3d1043@inbox.mailtrap.io");
        javaMailSender.send(mimeMessage);
    }

    private String getArticleHtml(ArticleDTO article){
        String content = "<div style=\" border-bottom: 1px solid black; margin-top: 30px; \">\n";
        content += "<h2 style=\" position: absolute; margin-left: 39%\">" + article.getTitle() + "</h2>\n";
        content += "<p style=\"color: red; position: absolute; margin-left: 39%; margin-top: 9%\">" + article.getDescription() + "</p>\n";
        content += "<img style=\" width:35%; margin-left: 40px; margin-bottom: 20px\"  src=" + article.getUrlToImage() + " />\n";
        content += "</div>";

        return content;
    }
}
