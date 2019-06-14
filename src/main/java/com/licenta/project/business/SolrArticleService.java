package com.licenta.project.business;

import com.licenta.project.business.dto.solr.SolrArticleDTO;
import com.licenta.project.entities.solr.SolrArticle;

import java.util.List;

public interface SolrArticleService {

    Iterable<SolrArticleDTO> getAllArticles();

    Iterable<SolrArticleDTO> fullSearch(String text);

    void deleteAll();
}
