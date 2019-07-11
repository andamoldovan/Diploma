package com.licenta.project.business.implementation;

import com.licenta.project.entities.Article;
import com.licenta.project.entities.ArticleSource;
import com.licenta.project.repositories.mongo.ArticleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {


    @Mock
    private ArticleRepository articleRepository;

    @Test
    public void setCollection() {
    }

    @Test
    public void getAllArticles() {
        Article article1 = new Article(new ArticleSource(null,"sourceName1"), "author1", "title1", "description1", "url1", "urlToImage1", "publishedDate1", "content1");
        Article article2 = new Article(new ArticleSource(null,"sourceName2"), "author2", "title2", "description2", "url2", "urlToImage2", "publishedDate2", "content2");

        Mockito.when(articleRepository.findAll()).thenReturn(Stream.of(article1, article2).collect(Collectors.toList()));

        List<Article> list = articleRepository.findAll();
        Assert.assertEquals(2, list.size());
        Mockito.verify(articleRepository).findAll();
    }

    @Test
    public void getArticlesByTitle() {
    }

    @Test
    public void getArticleById() {
    }
}