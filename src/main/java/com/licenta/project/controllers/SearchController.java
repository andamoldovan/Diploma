package com.licenta.project.controllers;

import com.licenta.project.business.ArticleService;
import com.licenta.project.business.dto.ArticleDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/search")
public class SearchController {

    private final ArticleService articleService;

    public SearchController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/basic-search", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<ArticleDTO> basicSearch(@RequestParam("domain") String domain, @RequestParam("field") String field){
        articleService.setCollection(domain);
        return articleService.getArticlesByTitle(field);
    }
}
