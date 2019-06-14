package com.licenta.project.entities.solr;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "articles")
public class SolrArticle {

    @Id
    @Indexed(name = "id", type = "string")
    @Field
    private String id;

    @Indexed(name = "source", type = "string", defaultValue = "")
    @Field
    private String source;

    @Indexed(name = "title", type = "string")
    @Field
    private String title;

    @Indexed(name = "author", type = "string", defaultValue = "")
    @Field
    private String author;

    @Indexed(name = "description", type = "string")
    @Field
    private String description;

    @Indexed(name = "content", type = "string")
    @Field
    private String content;

    public SolrArticle() {
    }


    public SolrArticle(String source, String title, String author, String description, String content) {
        this.source = source;
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
    }

    public SolrArticle(String id, String source, String title, String author, String description, String content) {
        this.id = id;
        this.source = source;
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SolrArticle{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
