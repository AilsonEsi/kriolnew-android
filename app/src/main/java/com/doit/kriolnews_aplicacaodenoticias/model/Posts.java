package com.doit.kriolnews_aplicacaodenoticias.model;

import java.util.Date;

public class Posts {

    private String id;
    private String title;
    private String description;
    private String category;
    private String content;
    private String imgUri;
    private String userId;
    private String pubDate;
    private String author;

    public Posts() {
    }

    public Posts(String title, String description, String category, String content, String imgUri, String userId, String pubDate, String author) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.content = content;
        this.imgUri = imgUri;
        this.userId = userId;
        this.pubDate = pubDate;
        this.author = author;
    }

    public Posts(String id, String title, String description, String category, String content, String imgUri, String userId, String pubDate,String author ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.content = content;
        this.imgUri = imgUri;
        this.userId = userId;
        this.pubDate = pubDate;
        this.author = author;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }
}