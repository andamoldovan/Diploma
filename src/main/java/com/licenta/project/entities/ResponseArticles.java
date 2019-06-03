package com.licenta.project.entities;

import java.util.List;

public class ResponseArticles {
    private List<Article> articles;

    public ResponseArticles() {
    }

    public ResponseArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "ResponseArticles{" +
                "articles=" + articles +
                '}';
    }
}
