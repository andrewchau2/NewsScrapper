package org.achau.controller;

import org.achau.model.newscrapper.IndependentNewsWebScrapper;
import org.achau.model.pojo.NewsItem;

import java.util.List;

public class IndependentJournalController {
    private final IndependentNewsWebScrapper webscrapper;
    
    public IndependentJournalController(){
        webscrapper = new IndependentNewsWebScrapper();
    }

    public IndependentJournalController(IndependentNewsWebScrapper webscrapper){
        this.webscrapper = webscrapper;
    }


    public List<NewsItem> getRecentUSNews(){
        return webscrapper.getRecentUSNews();
    }
    
    public List<NewsItem> getRecentPoliticsNews(){
        return webscrapper.getRecentPoliticsNews();
    }

    public List<NewsItem> getRecentWorldNews(){
        return webscrapper.getRecentWorldNews();
    }

    public void quit(){
        webscrapper.quit();
    }
}
