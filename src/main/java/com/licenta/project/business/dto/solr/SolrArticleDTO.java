package com.licenta.project.business.dto.solr;

public class SolrArticleDTO {

    private String id;
    private String source;
    private String title;
    private String author;
    private String description;
    private String content;
    String domain;

    public SolrArticleDTO() {
    }

    public SolrArticleDTO(String source, String title, String author, String description, String content, String domain) {
        this.source = source;
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
        this.domain = domain;
    }

    public SolrArticleDTO(String id, String source, String title, String author, String description, String content, String domain) {
        this.id = id;
        this.source = source;
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
        this.domain = domain;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
                ", domain='" + domain + '\'' +
                '}';
    }
}
