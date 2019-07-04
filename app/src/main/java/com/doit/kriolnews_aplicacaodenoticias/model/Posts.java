package com.doit.kriolnews_aplicacaodenoticias.model;

public class Posts {

    private String id;
    private String title;
    private String description;
    private String category;
    private String content;
    private String imgUri;

    public Posts() {
    }

    public Posts(String title, String description, String category, String content, String imgUri) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.content = content;
        this.imgUri = imgUri;
    }

    public Posts(String id, String title, String description, String category, String content, String imgUri) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.content = content;
        this.imgUri = imgUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
