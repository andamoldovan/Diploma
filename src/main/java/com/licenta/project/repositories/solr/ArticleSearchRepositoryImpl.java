package com.licenta.project.repositories.solr;

import com.licenta.project.entities.solr.SolrArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;

import javax.annotation.Resource;
import java.util.List;

public class ArticleSearchRepositoryImpl implements ArticleSearchRepository {

    @Resource
    private SolrTemplate solrTemplate;

    @Override
    public List<SolrArticle> fullTextSearch(String searchValue) {
        String[] words = searchValue.split(" ");

        Criteria conditions = createSearchConditions(words);
        SimpleQuery search = new SimpleQuery(conditions);

        Page results = solrTemplate.queryForPage("articles", search, SolrArticle.class);
        return results.getContent();
    }

    private Criteria createSearchConditions(String[] words){
        Criteria conditions = null;

        for(String word: words){
            if(conditions == null){
                conditions = new Criteria("title").contains(word)
                        .or(new Criteria("description").contains(word))
                        .or(new Criteria("content").contains(word));
            }else{
                conditions = conditions.or(new Criteria("title").contains(word)
                        .or(new Criteria("description").contains(word)))
                        .or(new Criteria("content").contains(word));
            }
        }
        return conditions;
    }
}
