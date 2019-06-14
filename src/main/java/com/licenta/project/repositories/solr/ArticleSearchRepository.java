package com.licenta.project.repositories.solr;

import com.licenta.project.entities.solr.SolrArticle;

import java.util.List;

public interface ArticleSearchRepository {

    List<SolrArticle> fullTextSearch(String searchValue);
}
