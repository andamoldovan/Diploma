package com.licenta.project.config;

import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.entities.Article;
import com.licenta.project.entities.User;
import com.licenta.project.repositories.ArticleRepository;
import com.licenta.project.repositories.UserRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DbSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public DbSeeder(UserRepository userRepository, ArticleRepository articleRepository){
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        BasicQuery query = new BasicQuery("{author : \"Eliott C. McLaughlin, CNN\"}");
        List<Article> result = articleRepository.findArticlesByAuthorAndTitleAndPublishedAt("Eliott C. McLaughlin, CNN",
                "Weeks after Nipsey Hussle's slaying, many questions remain unanswered ...","\"2019-04-21T18:50:00Z\"");

//        for(Article a : result){
//            System.out.println(a);
//        }



//        List<Article> art = articleRepository.findAll();
//        Article a = art.get(art.size() - 2);
//        String url = a.getUrl();
//        Document doc = Jsoup.connect(url).get();
//        Elements paragraphs = doc.select("p");
//        String content = "";
//        for(Element p : paragraphs){
//            content = content + p.text() + "\n";
//        }
//        a.setContent(content);
//        articleRepository.save(a);
//        System.out.println(a);

        Document doc = Jsoup.connect("https://www.nbcnews.com/news/us-news/guide-anti-abortion-laws-state-n1012566").get();
        //System.out.println(doc.getElementsByTag("body").text());
        System.out.println(doc.text());
    }
}
