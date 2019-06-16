package com.licenta.project.controllers;

import com.licenta.project.business.SolrArticleService;
import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.dto.solr.SolrArticleDTO;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin(maxAge = 3600)
@RestController()
@RequestMapping("/solr")
public class SolrController {
    private static final Logger logger = Logger.getLogger(SolrController.class);

    private final SolrArticleService solrArticleService;

    private static HashMap<String, Iterable<SolrArticleDTO>> allSearchResults = new HashMap<>();

    public SolrController(SolrArticleService solrArticleService) {
        this.solrArticleService = solrArticleService;
    }

//    @PostConstruct
//    public void setEnviroment(){
//        solrArticleService.deleteAll();
//    }

    @RequestMapping(value = "", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Iterable<SolrArticleDTO> getArticles(@RequestBody UserDTO userDTO ,
                                                @RequestParam(value = "chunk") Integer chunk,
                                                @RequestParam(value = "chunkSize", required = false) Integer chunkSize){
        logger.info("Get all articles with solr");
        if(chunk == 1) {
            Iterable<SolrArticleDTO> articles = solrArticleService.getAllArticles();
            if(allSearchResults.keySet().isEmpty()) allSearchResults.put(userDTO.getId(), articles);
            else{
                for(String user: allSearchResults.keySet()){
                    if(user.equals(userDTO.getId())) {
                        allSearchResults.remove(user);
                        allSearchResults.put(userDTO.getId(), articles);
                    }
                    else allSearchResults.put(userDTO.getId(), articles);
                }
            }
        }

        if(chunkSize == null) chunkSize = 20;
        Iterable<SolrArticleDTO> userArticles = (ArrayList<SolrArticleDTO>)allSearchResults.get(userDTO.getId());
        Iterable<SolrArticleDTO> result = new ArrayList<>();
        for(int i = (chunk - 1)*chunkSize; i< (chunk * chunkSize); i++){
            ((ArrayList<SolrArticleDTO>) result).add(((ArrayList<SolrArticleDTO>) userArticles).get(i));
        }
        return result;
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
