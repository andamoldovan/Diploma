package com.licenta.project.business.implementation;

import com.licenta.project.business.SolrArticleService;
import com.licenta.project.business.dto.solr.SolrArticleDTO;
import com.licenta.project.business.object_transformations.SolrArticleTransformation;
import com.licenta.project.entities.solr.SolrArticle;
import com.licenta.project.repositories.solr.SolrArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Iterable<SolrArticleDTO> getByTitle(String title) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByTitle(title);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            result.add(solrArticleTransformation.transform(article));
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByAuthor(String author) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByAuthor(author);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            result.add(solrArticleTransformation.transform(article));
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getBySource(String source) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findBySource(source);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            result.add(solrArticleTransformation.transform(article));
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByDomain(String domain) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByDomain(domain);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            result.add(solrArticleTransformation.transform(article));
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByContent(String content) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByContent(content);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            result.add(solrArticleTransformation.transform(article));
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByDescription(String description) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByDescription(description);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            result.add(solrArticleTransformation.transform(article));
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByAttribute(String text) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Page<SolrArticle> list = solrArticleRepository.findByNamedQuery(text, new PageRequest(0, 20));
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
