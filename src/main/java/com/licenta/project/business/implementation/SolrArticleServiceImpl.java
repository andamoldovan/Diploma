package com.licenta.project.business.implementation;

import com.licenta.project.business.SolrArticleService;
import com.licenta.project.business.dto.ArticleDTO;
import com.licenta.project.business.dto.solr.SolrArticleDTO;
import com.licenta.project.business.object_transformations.SolrArticleTransformation;
import com.licenta.project.entities.Article;
import com.licenta.project.entities.solr.SolrArticle;
import com.licenta.project.repositories.solr.SolrArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SolrArticleServiceImpl implements SolrArticleService {

    private final SolrArticleRepository solrArticleRepository;
    private final ArticleServiceImpl articleService;

    public SolrArticleServiceImpl(SolrArticleRepository solrArticleRepository, ArticleServiceImpl articleService) {
        this.solrArticleRepository = solrArticleRepository;
        this.articleService = articleService;
    }

    @Override
    public Iterable<SolrArticleDTO> getAllArticles() {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findAll();
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            SolrArticleDTO solrArticleDTO = getUrlForArticle(solrArticleTransformation.transform(article));
            result.add(solrArticleDTO);
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> fullSearch(String text) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.fullTextSearch(text);

        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            SolrArticleDTO solrArticleDTO = getUrlForArticle(solrArticleTransformation.transform(article));
            result.add(solrArticleDTO);
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByTitle(String title) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByTitle(title);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            SolrArticleDTO solrArticleDTO = getUrlForArticle(solrArticleTransformation.transform(article));
            result.add(solrArticleDTO);
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByAuthor(String author) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByAuthor(author);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            SolrArticleDTO solrArticleDTO = getUrlForArticle(solrArticleTransformation.transform(article));
            result.add(solrArticleDTO);
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getBySource(String source) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findBySource(source);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            SolrArticleDTO solrArticleDTO = getUrlForArticle(solrArticleTransformation.transform(article));
            result.add(solrArticleDTO);
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByDomain(String domain) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByDomain(domain);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            SolrArticleDTO solrArticleDTO = getUrlForArticle(solrArticleTransformation.transform(article));
            result.add(solrArticleDTO);
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByContent(String content) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByContent(content);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            SolrArticleDTO solrArticleDTO = getUrlForArticle(solrArticleTransformation.transform(article));
            result.add(solrArticleDTO);
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByDescription(String description) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Iterable<SolrArticle> list = solrArticleRepository.findByDescription(description);
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            SolrArticleDTO solrArticleDTO = getUrlForArticle(solrArticleTransformation.transform(article));
            result.add(solrArticleDTO);
        }
        return result;
    }

    @Override
    public Iterable<SolrArticleDTO> getByAttribute(String text) {
        SolrArticleTransformation solrArticleTransformation = new SolrArticleTransformation();
        Page<SolrArticle> list = solrArticleRepository.findByNamedQuery(text, new PageRequest(0, 20));
        List<SolrArticleDTO> result = new ArrayList<>();

        for(SolrArticle article : list){
            SolrArticleDTO solrArticleDTO = getUrlForArticle(solrArticleTransformation.transform(article));
            result.add(solrArticleDTO);
        }
        return result;
    }

    @Override
    public void deleteAll() {
        solrArticleRepository.deleteAll();
    }

    private SolrArticleDTO getUrlForArticle(SolrArticleDTO solrArticleDTO){
        List<String> collections = new ArrayList<>(Arrays.asList("articles", "business", "entertainment", "general", "health", "science", "sports", "technology"));
        for(String collectionName : collections){
            articleService.setCollection(collectionName);
            ArticleDTO art = articleService.getArticleById(solrArticleDTO.getId());
            if(art != null){
                solrArticleDTO.setUrl(art.getUrl());
                break;
            }
        }
        return solrArticleDTO;
    }
}
