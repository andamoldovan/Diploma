package com.licenta.project.business.dto.solr;

public class SolrArticleDTO {

    private String id;
    private String source;
    private String title;
    private String author;
    private String description;
    private String content;

    public SolrArticleDTO() {
    }

    public SolrArticleDTO(String source, String title, String author, String description, String content) {
        this.source = source;
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
    }

    public SolrArticleDTO(String id, String source, String title, String author, String description, String content) {
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
        return "SolrArticleDTO{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
