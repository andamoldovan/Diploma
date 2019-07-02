package com.licenta.project.controllers;

import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.UserDTO;
import com.licenta.project.business.implementation.ArticleServiceImpl;
import com.licenta.project.entities.Article;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("ALL")
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/top-headlines")
public class TopHeadlinesController {

    private static final Logger logger = Logger.getLogger(TopHeadlinesController.class);

    private final ArticleServiceImpl articleService;

    public TopHeadlinesController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<ArticleDTO> getChunk(@RequestBody UserDTO userDTO,
                            @RequestParam(value = "chunkSize", required = false) Integer chunkSize,
                            @RequestParam(value = "chunkNumber", required = false) Integer chunkNumber){
        logger.info("Get chunked top-headlines articles for user - " + userDTO.getId());
        String filepath = "D:/Users/andam/Documents/MEGA/_Diploma/server-logs/user-article-files/current_article_list_" + userDTO.getId();
        File file = new File(filepath);
        if(chunkNumber == 0) {
            logger.info("User file -" + userDTO.getId() + " - deleted");
            file.delete();
            chunkNumber = 1;
        }
        if(!file.exists()){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(filepath);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOutputStream);
                articleService.setCollection("articles");
                List<ArticleDTO> articles = articleService.getAllArticles();
                objectOut.writeInt(articles.size());
                for(ArticleDTO art : articles){
                    objectOut.writeObject(art);
                }
                objectOut.flush();
                objectOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(chunkSize == null) chunkSize = 20;
        if(chunkNumber == null) chunkNumber = 1;

        try{
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
            int fileSize = objectInputStream.readInt();
            List<ArticleDTO> articles = new ArrayList<>();
            while (fileSize > 0){
                ArticleDTO art = (ArticleDTO) objectInputStream.readObject();
                articles.add(art);
                fileSize--;
            }
            objectInputStream.close();
            Collections.reverse(articles);
            List<ArticleDTO> result = new ArrayList<>();
            for(int i = (chunkNumber - 1)*chunkSize; i < (chunkNumber * chunkSize) - 1; i++){
                result.add(articles.get(i));
            }
            return result;
        }catch(Exception e){
            logger.error("Error getting chunk for -" + userDTO.getId());
            e.printStackTrace();
        }
        return null;
    }

}
