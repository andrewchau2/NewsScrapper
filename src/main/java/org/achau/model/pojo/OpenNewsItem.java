package org.achau.model.pojo;

/**
 * Pojo class for the Open News object.
 * Each item scrapped from the Open News Website will be represented in a OpenNewsItem object
 * Note: This class does not have a thumbnail url.
 * @author Andrew Chau
 * @version 1.0
 */
public class OpenNewsItem extends NewsItem{
    private final String url;
    private final String description;
    private final String title;

    /**
     * Constructor for the OpenNewsItem. This is the only constructor for this class
     * @param url String of the news article
     * @param description String of the news description
     * @param title String of the news title
     */
    public OpenNewsItem(String url, String description,String title){
        this.url = url;
        this.description = description;
        this.title = title;
    }

    /**
     * Returns the url of the Open News article
     * @return string of the url
     */
    @Override
    public String getUrl(){
        return url;
    }

    /**
     * Returns the description of the Open News article
     * @return string of the description
     */
    @Override
    public String getDescription(){
        return description;
    }

    /**
     * Return the title of the Open News article
     * @return string of the title
     */
    @Override
    public String getTitle(){
        return title;
    }

    /**
     * Returns the string of the OpenNewsItem object in string format
     * @return string of the OpenNewsItem object
     */
    @Override
    public String toString() {
        return "OpenNewsItem{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
