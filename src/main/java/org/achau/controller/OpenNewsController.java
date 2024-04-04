package org.achau.controller;

import org.achau.model.newscrapper.OpenNewsWebscrapper;
import org.achau.model.pojo.NewsItem;

import java.util.List;

public class OpenNewsController {

    private OpenNewsWebscrapper openNewsWebscrapper;

    public OpenNewsController(){
        openNewsWebscrapper = new OpenNewsWebscrapper();
    }
    public OpenNewsController(OpenNewsWebscrapper o){
        openNewsWebscrapper = o;
    }


    public List<NewsItem> getRecentNews(){
        return openNewsWebscrapper.getRecentNews();
    }

    public String getDescription(){
        return openNewsWebscrapper.getDescription();
    }

    public String getUrl(){
        return openNewsWebscrapper.getUrl();
    }

    public void quit(){
        openNewsWebscrapper.quit();
    }



}
