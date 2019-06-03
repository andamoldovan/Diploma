package com.licenta.project.business.implementation;

import com.licenta.project.business.ArticleService;
import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.object_transformations.ArticleTransformation;
import com.licenta.project.entities.Article;
import com.licenta.project.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
//        articleRepository.setCollectionName("articles");
        System.out.println("COLLECTION NAME");
        System.out.println(this.articleRepository.getCollectionName());
        List<Article> articles = articleRepository.findArticleByTitleRegex(title);
        List<ArticleDTO> result = new ArrayList<>();
        for(Article a : articles){
            result.add(articleTransformation.transform(a));
        }
        return result;
    }
}
