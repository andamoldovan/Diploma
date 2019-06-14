package com.licenta.project.business.object_transformations;

import com.licenta.project.business.dto.solr.SolrArticleDTO;
import com.licenta.project.entities.solr.SolrArticle;

public class SolrArticleTransformation {

    public SolrArticleDTO transform(SolrArticle solrArticle){
        SolrArticleDTO solrArticleDTO = new SolrArticleDTO(solrArticle.getId(), solrArticle.getSource(), solrArticle.getTitle(),
                solrArticle.getAuthor(), solrArticle.getDescription(), solrArticle.getContent());
        return solrArticleDTO;
    }
}
