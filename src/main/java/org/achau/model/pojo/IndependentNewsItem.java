package org.achau.model.pojo;

/**
 * Pojo class for the Independent News Journal object.
 * Each item scrapped from the Independent News Journal Website will be represented in a IndependentNewsItem object
 * @author Andrew Chau
 * @version 1.0
 */
public class IndependentNewsItem extends NewsItem{

    private final String articleUrl;
    private final String description;
    private final String title;
    private final String thumbnailUrl;

    /**
     * Construtor for the IndependentNewsItem
     * @param articleUrl String of the news article
     * @param description String of the news description
     * @param title String of the news title
     * @param thumbnailUrl String of the news thumbnail picture
     */
    public IndependentNewsItem(String articleUrl, String description, String title, String thumbnailUrl){
        this.articleUrl = articleUrl;
        this.description = description;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     * Returns the url of the Independent News article
     * @return string of the url
     */
    @Override
    public String getUrl() {
        return articleUrl;
    }

    /**
     * Returns the description of the Independent News article
     * @return string of the description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the title of the Independent News article
     * @return string of the title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Returns the thumbnail url of the Independent News article
     * @return string of the thumbnail url
     */
    public String getThumbnailUrl(){return thumbnailUrl;}


    /**
     * Returns the string of the OpenNewsItem object in string format
     * @return string of the IndependentNewsItem object
     */
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
