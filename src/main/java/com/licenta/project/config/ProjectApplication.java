package com.licenta.project.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.licenta.project.entities.Article;
import com.licenta.project.entities.ResponseArticles;
import com.licenta.project.scheduler.Scheduler;
import com.licenta.project.scheduler.tasks.TopHeadlines;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.List;

@ComponentScan("com.licenta.project")
@EnableMongoRepositories("com.licenta.project.repositories")
@EnableScheduling
@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjectApplication.class, args);
        //Scheduler sch = new Scheduler();
//        TopHeadlines top = new TopHeadlines();
//        System.out.println(top.getHeadlinesByCountry("us"));

//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//
//            ResponseArticles art = objectMapper.readValue(top.getHeadlinesByCountry("us"), ResponseArticles.class);
//            for(Article a : art.getArticles()){
//                top.fileWriter(a.toString());
//            }
//            System.out.println(art.getArticles().size());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
