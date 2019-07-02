package com.licenta.project.business.services;

import com.licenta.project.business.dto.solr.SolrArticleDTO;
import com.licenta.project.entities.solr.SolrArticle;

import java.util.List;

public interface SolrArticleService {

    Iterable<SolrArticleDTO> getAllArticles();

    Iterable<SolrArticleDTO> fullSearch(String text);

    Iterable<SolrArticleDTO> getByTitle(String title);

    Iterable<SolrArticleDTO> getByAuthor(String author);

    Iterable<SolrArticleDTO> getBySource(String source);

    Iterable<SolrArticleDTO> getByDomain(String domain);

    Iterable<SolrArticleDTO> getByContent(String content);

    Iterable<SolrArticleDTO> getByDescription(String description);

    Iterable<SolrArticleDTO> getByAttribute(String text);

    void deleteAll();
}
