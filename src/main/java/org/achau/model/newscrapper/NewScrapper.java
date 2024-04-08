package org.achau.model.newscrapper;


/**
 * Abstract Class of NewsScrapper Object.
 * Each class that inherits from this class must declare their on getUrl() and getDescription() methods.
 * @author Andrew Chau
 * @version 1.0
 */
public abstract class NewScrapper {
    /**
     * Abstract method that requires the designer to provide a news article url.
     * This url will be the starting point of where the webscrapping will begin
     * @return string of the news article website
     */
    public abstract String getUrl();

    /**
     * Abstract method that requires the designer to provide a description of news article site.
     * This method will provide users a general description of the website being scrapped along with any
     * additional information the designer wishes to provide
     * @return string of the description for the news article site
     */
    public abstract String getDescription();

}
