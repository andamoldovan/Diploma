package com.licenta.project.repositories;

import org.springframework.stereotype.Component;

@Component("articleRepositoryCustom")
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom  {

    private static String collectionName = "articles";

    @Override
    public String getCollectionName() {
        return collectionName;
    }

    @Override
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
