package com.licenta.project.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.licenta.project.entities.Article;
import com.licenta.project.entities.ResponseArticles;
import com.licenta.project.repositories.ArticleRepository;
import com.licenta.project.scheduler.tasks.FileCleanup;
import com.licenta.project.scheduler.tasks.TopHeadlines;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class Scheduler{

    private static final Logger logger = Logger.getLogger(Scheduler.class);

    private final ArticleRepository articleRepository;

    public Scheduler(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //@Scheduled(fixedRate = 1000*60)
    public void populateArticleTable(){

        TopHeadlines top = new TopHeadlines();
        System.out.println(top.getHeadlinesByCountry("us"));
        articleRepository.setCollectionName("articles");
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            ResponseArticles art = objectMapper.readValue(top.getHeadlinesByCountry("us"), ResponseArticles.class);

            System.out.println(art.getArticles().size());
            List<Article> filteredList = eliminateDuplicates(art.getArticles());

            logger.debug("Top-headlined from US saved in the database. Number of articles saved: " + filteredList.size());
            for(Article a : filteredList){
                articleRepository.save(getOnlineContent(a));
            }

        } catch (IOException e) {
            logger.error("Error at saving US top-headlines in the database");
            e.printStackTrace();
        }
    }

   // @Scheduled(fixedRate = 1700 * 60)
    public void populateBusinessTable(){
        populateTableByDomain("business");
    }

   // @Scheduled(fixedRate = 2300 * 60)
    public void populateEntertainmentTable(){
        populateTableByDomain("entertainment");
    }

    //@Scheduled(fixedRate = 3500 * 60)
    public void populateHealthTable(){
        populateTableByDomain("health");
    }

   // @Scheduled(fixedRate = 4800 * 60)
    public void populateGeneralTable(){
        populateTableByDomain("general");
    }

   // @Scheduled(fixedRate = 5900 * 60)
    public void populateScienceTable(){
        populateTableByDomain("science");
    }

   // @Scheduled(fixedRate = 5300 * 60)
    public void populateSportsTable(){
        populateTableByDomain("sports");
    }

   // @Scheduled(fixedRate = 1700 * 60)
    public void populateTechnologyTable(){
        populateTableByDomain("technology");
    }

    @Scheduled(fixedRate = 1000*60*60)
    public void cleanUpFileUserFiles(){
        FileCleanup fileCleanup = new FileCleanup();
        String mainFolderPath = "D:/Users/andam/Documents/MEGA/_Diploma/server-logs/user-article-files";
        fileCleanup.cleanUp(mainFolderPath);
    }

    public void populateTableByDomain(String domain){
        TopHeadlines top = new TopHeadlines();
        ObjectMapper objectMapper = new ObjectMapper();
        articleRepository.setCollectionName(domain);
        try {

            ResponseArticles art = objectMapper.readValue(top.getHeadlinesByCategory("us", domain), ResponseArticles.class);

            List<Article> filteredList = eliminateDuplicates(art.getArticles());

            logger.debug(domain + " articles from US saved in the database. Number of articles saved: " + filteredList.size());
            for(Article a : filteredList){
                articleRepository.save(getOnlineContent(a));
            }

        } catch (IOException e) {
            logger.error("Error at saving US " + domain + " articles in the database");
            e.printStackTrace();
        }
    }

    private List<Article> eliminateDuplicates(List<Article> list){
        List<Article> filteredList = new ArrayList<>();
        for(Article art : list) {
            List<Article> result = articleRepository.findArticlesByAuthorAndTitleAndPublishedAt(art.getAuthor(), art.getTitle(), art.getPublishedAt());
            if(result.size() == 0){
                filteredList.add(art);
            }
        }
        return filteredList;
    }

    private Article getOnlineContent(Article article){
        String url = article.getUrl();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements paragraphs = doc.select("p");
            String content = "";
            for(Element p : paragraphs){
                content = content + p.text() + "\n";
            }
            article.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return article;
    }


}