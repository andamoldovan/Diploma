package com.licenta.project.business;

import com.licenta.project.business.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {

    void setCollection(String collectionName);

    List<ArticleDTO> getAllArticles();

    List<ArticleDTO> getArticlesByTitle(String title);

}
