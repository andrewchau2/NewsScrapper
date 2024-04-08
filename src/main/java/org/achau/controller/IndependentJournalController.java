package org.achau.controller;

import org.achau.model.newscrapper.IndependentNewsWebScrapper;
import org.achau.model.pojo.NewsItem;

import java.util.List;

/**
 * Wrapper Class for the IndependentNewsWebScrapper object.
 * Handles all IndependentNewsWebScrapper method calls and outputs them in an required format for the view
 * @author Andrew Chau
 * @version 1.0
 */
public class IndependentJournalController {
    private final IndependentNewsWebScrapper webscrapper;

    /**
     * Constructor for the IndependentJournalController class
     * The only purpose is to initialize the IndependentNewsWebScrapper object
     */
    public IndependentJournalController(){
        webscrapper = new IndependentNewsWebScrapper();
    }

    /**
     * Constructor for the IndependentJournalController class
     * The only purpose is to initialize the IndependentNewsWebScrapper object
     */
    public IndependentJournalController(IndependentNewsWebScrapper webscrapper){
        this.webscrapper = webscrapper;
    }


    /**
     * Wrapper for the web-scrapper getRecentUSNews operation
     * If classes in the "view" package needs to specify the data in a different format, it should be done here
     * @return a List of NewsItem that was scrapped
     */
    public List<NewsItem> getRecentUSNews(){
        return webscrapper.getRecentUSNews();
    }

    /**
     * Wrapper for the web-scrapper getRecentPoliticsNews operation
     * If classes in the "view" package needs to specify the data in a different format, it should be done here
     * @return a List of NewsItem that was scrapped
     */
    public List<NewsItem> getRecentPoliticsNews(){
        return webscrapper.getRecentPoliticsNews();
    }

    /**
     * Wrapper for the web-scrapper getRecentWorldNews operation
     * If classes in the "view" package needs to specify the data in a different format, it should be done here
     * @return a List of NewsItem that was scrapped
     */
    public List<NewsItem> getRecentWorldNews(){
        return webscrapper.getRecentWorldNews();
    }

    /**
     * Wrapper for the web-scrapper quit operation
     * This method should always be called before exiting the application
     */
    public void quit(){
        webscrapper.quit();
    }
}
