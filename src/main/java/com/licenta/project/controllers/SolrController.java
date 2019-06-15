package com.licenta.project.controllers;

import com.licenta.project.business.SolrArticleService;
import com.licenta.project.business.dto.solr.SolrArticleDTO;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController()
@RequestMapping("/solr")
public class SolrController {
    private static final Logger logger = Logger.getLogger(SolrController.class);

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
        logger.info("Get all articles with solr");
        return solrArticleService.getAllArticles();
    }

    @RequestMapping(value = "/fullTextSearch", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Iterable<SolrArticleDTO> getArticlesByQuery(@RequestParam(value = "text") String text){
        logger.info("Get full text search for articles with solr");
        return  solrArticleService.fullSearch(text);
    }

    @RequestMapping(value = "/anyAttributeSearch", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Iterable<SolrArticleDTO> getByAnyAttribute(@RequestParam(value = "text") String text){
        logger.info("Get search by attribute for articles with solr");
        return solrArticleService.getByAttribute(text);
    }

    @RequestMapping(value = "/attributeSearch", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Iterable<SolrArticleDTO> getByAttribute(@RequestParam(value = "text") String text,
                                                   @RequestParam(value = "attr") String attribute){
        logger.info("Get search by a single attribute for articles with solr");
        if(attribute.equals("title")) return solrArticleService.getByTitle(text);
        if(attribute.equals("author")) return solrArticleService.getByAuthor(text);
        if(attribute.equals("source")) return solrArticleService.getBySource(text);
        if(attribute.equals("domain")) return solrArticleService.getByDomain(text);
        if(attribute.equals("content")) return solrArticleService.getByContent(text);
        if(attribute.equals("description")) return solrArticleService.getByDescription(text);
        return null;
    }
}
