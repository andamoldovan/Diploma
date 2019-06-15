package com.licenta.project.repositories.solr;


import com.licenta.project.entities.solr.SolrArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrArticleRepository extends ArticleSearchRepository, SolrCrudRepository<SolrArticle, String> {

    @Query("title:(?0...)")
    Iterable<SolrArticle> findByTitle(String searchItem);

    @Query("author:(?0...)")
    Iterable<SolrArticle> findByAuthor(String searchItem);

    @Query("source:(?0...)")
    Iterable<SolrArticle> findBySource(String searchItem);

    @Query("domain:(?0...)")
    Iterable<SolrArticle> findByDomain(String searchItem);

    @Query("content:(?0...)")
    Iterable<SolrArticle> findByContent(String searchItem);

    @Query("description:(?0...)")
    Iterable<SolrArticle> findByDescription(String searchItem);

    @Query(name = "SolrArticle.findByNamedQuery")
    Page<SolrArticle> findByNamedQuery(String searchTerm, Pageable pageable);

}
