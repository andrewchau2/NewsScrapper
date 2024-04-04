package org.achau.model.pojo;

public class IndependentNewsItem extends NewsItem{

    private final String articleUrl;
    private final String description;
    private final String title;
    private final String thumbnailUrl;

    public IndependentNewsItem(String articleUrl, String description, String title, String thumbnailUrl){
        this.articleUrl = articleUrl;
        this.description = description;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }


    @Override
    public String getUrl() {
        return articleUrl;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl(){return thumbnailUrl;}

    @Override
    public String toString() {
        return "IndependentNewsItem{" +
                "articleUrl='" + articleUrl + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
