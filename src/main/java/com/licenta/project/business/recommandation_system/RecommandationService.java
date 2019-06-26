package com.licenta.project.business.recommandation_system;

import com.licenta.project.business.ArticleService;
import com.licenta.project.business.UserService;
import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommandationService {

    private final ArticleService articleService;

    public RecommandationService(ArticleService articleService) {
        this.articleService = articleService;
    }


    public List<ArticleDTO> getRecommandationForUser(ArrayList<UserDTO> allUsers, UserDTO userDTO){
        List<ArticleDTO> articles = articleService.getAllArticles();
        List<ArticleDTO> predictions = new ArrayList<>();

        Recommender recommender = new Recommender();

        for(ArticleDTO article: articles){
            double d = recommender.predictRating(allUsers, userDTO, article.getId());
            if(d >= 3){
                ArticleDTO articleDTO = articleService.getArticleById(article.getId());
                predictions.add(articleDTO);
            }
        }
        return predictions;
    }
}
