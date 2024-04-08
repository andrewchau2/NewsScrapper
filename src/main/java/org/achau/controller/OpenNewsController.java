package org.achau.controller;

import org.achau.model.newscrapper.OpenNewsWebscrapper;
import org.achau.model.pojo.NewsItem;

import java.util.List;

/**
 * Wrapper Class for the OpenNewsController object.
 * Handles all OpenNewsController method calls and outputs them in an required format for the view
 * @author Andrew Chau
 * @version 1.0
 */
public class OpenNewsController {

    private OpenNewsWebscrapper openNewsWebscrapper;

    public OpenNewsController(){
        openNewsWebscrapper = new OpenNewsWebscrapper();
    }
    public OpenNewsController(OpenNewsWebscrapper o){
        openNewsWebscrapper = o;
    }

    /**
     * Wrapper for the web-scrapper getRecentNews operation
     * If classes in the "view" package needs to specify the data in a different format, it should be done here
     * @return a List of NewsItem that was scrapped
     */
    public List<NewsItem> getRecentNews(){
        return openNewsWebscrapper.getRecentNews();
    }

    /**
     * Wrapper for the web-scrapper getDescription operation
     * @return a string of the scrapper description
     */
    public String getDescription(){
        return openNewsWebscrapper.getDescription();
    }

    /**
     * Wrapper for the web-scrapper getUrl operation
     * @return a string of the scrapper url
     */
    public String getUrl(){
        return openNewsWebscrapper.getUrl();
    }

    /**
     * Wrapper for the web-scrapper quit operation
     * This method should always be called before exiting the application
     */
    public void quit(){
        openNewsWebscrapper.quit();
    }



}
