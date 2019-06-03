package com.licenta.project.business.dto;

import java.io.Serializable;

public class ArticleDTO implements Serializable {

    private String id;
    private ArticleSourceDTO articleSourceDTO;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public ArticleDTO() {
    }

    public ArticleDTO(ArticleSourceDTO articleSourceDTO, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.articleSourceDTO = articleSourceDTO;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArticleSourceDTO getArticleSourceDTO() {
        return articleSourceDTO;
    }

    public void setArticleSourceDTO(ArticleSourceDTO articleSourceDTO) {
        this.articleSourceDTO = articleSourceDTO;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id='" + id + '\'' +
                ", articleSourceDTO=" + articleSourceDTO +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
