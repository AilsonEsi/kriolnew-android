package com.doit.kriolnews_aplicacaodenoticias.model;

public class NewsItem {

    private String title;
    private String link;
    private String description;
    private String pubDate;
    private String thumbnailUrl;
    private String category;

    public NewsItem() {
    }

    public NewsItem(String title, String link, String description, String pubDate, String thumbnailUrl, String category) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.thumbnailUrl = thumbnailUrl;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
