package com.licenta.project.business.implementation;

import com.licenta.project.business.SolrArticleService;
import com.licenta.project.business.dto.solr.SolrArticleDTO;
import com.licenta.project.business.object_transformations.SolrArticleTransformation;
import com.licenta.project.entities.solr.SolrArticle;
import com.licenta.project.repositories.solr.SolrArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SolrArticleServiceImpl implements SolrArticleService {

    private final SolrArticleRepository solrArticleRepository;

    public SolrArticleServiceImpl(SolrArticleRepository solrArticleRepository) {
        this.solrArticleRepository = solrArticleRepository;
    }

    @Override
    public Iterable<SolrArticleDTO> getAllArticles() {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findAll();
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            result.add(solrArticleTransformation.transform(article));
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> fullSearch(String text) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.fullTextSearch(text);

        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            result.add(solrArticleTransformation.transform(article));
        }
        return result;
    }

    @Override
    public void deleteAll() {
        solrArticleRepository.deleteAll();
    }
}
