package com.licenta.project.controllers;


import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.implementation.ArticleServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/favorites")
public class FavoriteArticleController {

    private final ArticleServiceImpl articleService;

    public FavoriteArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/getArticle", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<ArticleDTO> getArticle(@RequestBody UserDTO userDTO){


        List<String> collections = new ArrayList<>(Arrays.asList("articles", "business", "entertainment", "general", "health", "science", "sports", "technology"));
        List<String> articleIds = userDTO.getFavoriteArticles();
        List<ArticleDTO> result = new ArrayList<>();

        for(String id: articleIds) {
            for (String collectionName : collections) {
                articleService.setCollection(collectionName);
                ArticleDTO article = articleService.getArticleById(id);
                if(article != null) {
                    result.add(article);
                    break;
                }
            }
        }

        return result;
    }
}
