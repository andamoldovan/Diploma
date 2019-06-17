package com.licenta.project.scheduler.tasks;

import com.licenta.project.business.ArticleService;
import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.implementation.UserServiceImpl;
import com.licenta.project.config.EmailServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
            String text = "";
            for(ArticleDTO art : articles){
                text += art.getTitle() + "  \n  ";
            }
            emailService.sendMail(user.getEmail(), "News update", text);
        }
    }
}
