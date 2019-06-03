package com.licenta.project.business.object_transformations;

import com.licenta.project.business.dto.ArticleSourceDTO;
import com.licenta.project.entities.ArticleSource;

public class ArticleSourceTransformation {

    public ArticleSourceDTO transform(ArticleSource articleSOurce){
        return new ArticleSourceDTO(articleSOurce.getId(), articleSOurce.getName());
    }
}
