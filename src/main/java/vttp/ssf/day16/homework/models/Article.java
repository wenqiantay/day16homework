package vttp.ssf.day16.homework.models;

import org.springframework.stereotype.Component;

@Component
public class Article {
    
    private String title;
    private String description;
    private String imageUrl;
    private String articleUrl;

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
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getArticleUrl() {
        return articleUrl;
    }
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
    
    @Override
    public String toString() {
        return "Article [title=" + title + ", description=" + description + ", imageUrl=" + imageUrl + ", articleUrl="
                + articleUrl + "]";
    }

    
}
