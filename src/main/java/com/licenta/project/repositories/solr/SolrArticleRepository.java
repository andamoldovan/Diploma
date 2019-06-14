package com.licenta.project.repositories.solr;


import com.licenta.project.entities.solr.SolrArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrArticleRepository extends ArticleSearchRepository, SolrCrudRepository<SolrArticle, String> {

    @Query("title:(?0...)")
    Page<SolrArticle> findByCustomQuery(String searchItem, Pageable pageable);

    @Query(name = "SolrArticle.findByNamedQuery")
    Page<SolrArticle> findByNamedQuery(String searchTerm, Pageable pageable);

}
