package com.licenta.project.scheduler.tasks;

import com.licenta.project.business.services.ArticleService;
import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.implementation.UserServiceImpl;
import com.licenta.project.config.EmailServiceImpl;

import javax.mail.MessagingException;
import java.util.*;

public class EmailScheduling {

    private final UserServiceImpl userService;
    private final ArticleService articleService;

    public EmailScheduling(UserServiceImpl userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    public void scheduleEmail(String time){

        EmailServiceImpl emailService = new EmailServiceImpl();

        List<UserDTO> users = userService.getUsersWithEmailSchedules(time);

        for(UserDTO user: users){
            List<ArticleDTO> articles = new ArrayList<>();
            for(String str: user.getPreferences()){
                articleService.setCollection(str);
                List<ArticleDTO> allArticles = articleService.getAllArticles();
                int size = allArticles.size();
                if((size - 1) > 0) articles.add(allArticles.get(size - 1));
                if((size - 2) > 0) articles.add(allArticles.get(size - 2));
                if((size - 3) > 0) articles.add(allArticles.get(size - 3));
            }
            try {
                emailService.sendMimeMail(articles, user.getEmail());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
