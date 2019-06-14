package com.licenta.project.controllers;

import com.licenta.project.business.SolrArticleService;
import com.licenta.project.business.dto.solr.SolrArticleDTO;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/solr")
public class SolrController {

    private final SolrArticleService solrArticleService;

    public SolrController(SolrArticleService solrArticleService) {
        this.solrArticleService = solrArticleService;
    }

//    @PostConstruct
//    public void setEnviroment(){
//        solrArticleService.deleteAll();
//    }

    @RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Iterable<SolrArticleDTO> getArticles(){
        return solrArticleService.getAllArticles();
    }

    @RequestMapping(value = "/fullTextSearch", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Iterable<SolrArticleDTO> getArticlesByQuery(@RequestParam(value = "text") String text){
       return  solrArticleService.fullSearch(text);
    }

}
