package com.licenta.project.repositories.mongo;

import com.licenta.project.entities.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String>, ArticleRepositoryCustom {

    //@Query("{'author' : ?0, 'title' : ?1, 'publishedDate' : ?2}")
    List<Article> findArticlesByAuthorAndTitleAndPublishedAt(String author, String title, String date);

    @Query("{ 'title' : { $regex: ?0 } }")
    List<Article> findArticleByTitleRegex(String title);

    List<Article> findArticleByAuthorAndPublishedAt(String author, String date);
}
