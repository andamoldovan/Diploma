package com.licenta.project.business.dto;

import java.io.Serializable;

public class ArticleSourceDTO implements Serializable {

    private String id;
    private String name;

    public ArticleSourceDTO() {
    }

    public ArticleSourceDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ArticleSourceDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
