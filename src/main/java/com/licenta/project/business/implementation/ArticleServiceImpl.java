package com.licenta.project.business.implementation;

import com.licenta.project.business.ArticleService;
import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.object_transformations.ArticleTransformation;
import com.licenta.project.entities.Article;
import com.licenta.project.repositories.mongo.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private ArticleTransformation articleTransformation;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        this.articleTransformation = new ArticleTransformation();

    }

    @Override
    public void setCollection(String collectionName) {
        this.articleRepository.setCollectionName(collectionName);
    }

    @Override
    public List<ArticleDTO> getAllArticles(){
        List<Article> articles = articleRepository.findAll();
        List<ArticleDTO> result = new ArrayList<>();
        for(Article a : articles){
            result.add(articleTransformation.transform(a));
        }
        return result;
    }

    @Override
    public List<ArticleDTO> getArticlesByTitle(String title) {
        List<Article> articles = articleRepository.findArticleByTitleRegex(title);
        List<ArticleDTO> result = new ArrayList<>();
        for(Article a : articles){
            result.add(articleTransformation.transform(a));
        }
        return result;
    }

    @Override
    public ArticleDTO getArticleById(String id) {
        Optional<Article> art = articleRepository.findById(id);
        if(art.isPresent()) return articleTransformation.transform(art.get());
        return null;
    }
}
