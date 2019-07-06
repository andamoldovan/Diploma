package com.licenta.project.controllers;

import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.services.ArticleService;
import com.licenta.project.business.dto.ArticleDTO;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/search")
public class SearchController {

    private static final Logger logger = Logger.getLogger(SearchController.class);

    private final ArticleService articleService;

    public SearchController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/basic-search", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<ArticleDTO> basicSearch(@RequestParam("domain") String domain, @RequestParam("field") String field){
        logger.info("Basic search api used on domain - " + domain);
        if ((!domain.equals("headlines"))) {
            articleService.setCollection(domain);
        } else {
            articleService.setCollection("articles");
        }
        return articleService.getArticlesByTitle(field);
    }

    @RequestMapping(value = "/basic-search/favorites", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<ArticleDTO> basicFavortesSearch(@RequestBody  UserDTO userDTO, @RequestParam("field") String field){
        logger.info("Basic search api used on user profile articles");
        List<String> favoriteArticlesIds = userDTO.getFavoriteArticles();

        List<String> collections = new ArrayList<>(Arrays.asList("articles", "business", "entertainment", "general", "health", "science", "sports", "technology"));
        List<ArticleDTO> allSearchedArticles = new ArrayList<>();
        List<ArticleDTO> result = new ArrayList<>();

        for(String collection: collections){
            articleService.setCollection(collection);
            allSearchedArticles.addAll(articleService.getArticlesByTitle(field));
        }

        for(String articleId: favoriteArticlesIds){
            for(ArticleDTO article: allSearchedArticles){
                if(article.getId().equals(articleId)){
                    result.add(article);
                    break;
                }
            }
        }
        return result;
    }
}
