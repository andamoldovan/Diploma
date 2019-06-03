package com.licenta.project.business.object_transformations;

import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.ArticleSourceDTO;
import com.licenta.project.entities.Article;

public class ArticleTransformation {

    public ArticleDTO transform(Article article){
        ArticleSourceTransformation trans = new ArticleSourceTransformation();
        ArticleSourceDTO articleSourceDTO = trans.transform(article.getSource());
        ArticleDTO articleDTO = new ArticleDTO(articleSourceDTO, article.getAuthor(), article.getTitle(), article.getDescription(), article.getUrl(),
                article.getUrlToImage(), article.getPublishedAt(), article.getContent());

        articleDTO.setId(article.getId());
        return articleDTO;
    }
}
