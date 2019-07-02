package com.licenta.project.config;

import com.licenta.project.business.services.UserService;
import com.licenta.project.entities.Article;
import com.licenta.project.repositories.mongo.ArticleRepository;
import com.licenta.project.repositories.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DbSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Autowired
    public DbSeeder(UserRepository userRepository, ArticleRepository articleRepository, UserService userService){
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {


        BasicQuery query = new BasicQuery("{author : \"Eliott C. McLaughlin, CNN\"}");
        List<Article> result = articleRepository.findArticlesByAuthorAndTitleAndPublishedAt("Eliott C. McLaughlin, CNN",
                "Weeks after Nipsey Hussle's slaying, many questions remain unanswered ...","\"2019-04-21T18:50:00Z\"");


//        ArrayList<String> preferences = new ArrayList<>(Arrays.asList("business", "sports"));
//        HashMap<String, Integer> hash = new HashMap<>();
//        hash.put("a1", 1);
//        hash.put("a2", 2);
//        UserDTO user = new UserDTO("b", "b", "b", "b", "b",  preferences, 0, new ArrayList<>(), "12.00", hash);
//        userService.saveUser(user);
    }
}
