package org.achau.model.newscrapper;

import org.achau.logger.FileLogger;
import org.achau.model.pojo.IndependentNewsItem;
import org.achau.model.pojo.NewsItem;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class IndependentNewsWebScrapper extends NewScrapper {

    private String url;
    private final WebDriver webDriver;
    private String description;

    public static final String politicsUrl = "https://ijr.com/tag/politics/";
    public static final String usNewsUrl = "https://ijr.com/tag/us-news/";

    public static final String worldNewsUrl = "https://ijr.com/tag/world-news/";

    private static final String logHeader = "IndependentNewsWebScrapper: ";
    public IndependentNewsWebScrapper(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-minimized");
        options.addArguments("--headless");
        webDriver = new EdgeDriver(options);
        this.url = "https://ijr.com/";
        this.description = """
               Webscrapped News Articles from Independent Journal News (https://ijr.com/)
               These webscrapped captures top news articles along with a short description about them.
               For more info on the article, click on the link provided.
               """;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public List<NewsItem> getRecentPoliticsNews(){
        FileLogger.logger.log(Level.INFO, logHeader + "Executing Recent Politics Scrapping");
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        List<NewsItem> newsList =  wait.until(d -> {
            performGetRecentPoliticsSetUp();
            return performWebpageScrapping();
        });
        FileLogger.logger.log(Level.INFO, logHeader + "Completed Recent Politics Scrapping");
        return newsList;
    }

    public List<NewsItem> getRecentUSNews(){
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        List<NewsItem> newsList =  wait.until(d -> {
            FileLogger.logger.log(Level.INFO, logHeader + "Executing Recent US News Scrapping");
            performGetRecentUSNewsSetUp();
            return performWebpageScrapping();
        });
        FileLogger.logger.log(Level.INFO, logHeader + "Completed Recent US News Scrapping");
        return newsList;
    }


    public List<NewsItem> getRecentWorldNews(){
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        List<NewsItem> newsList = wait.until(d -> {
            FileLogger.logger.log(Level.INFO, logHeader + "Executing Recent World News Scrapping");
            performGetRecentWorldNewsSetUp();
            return performWebpageScrapping();
        });
        FileLogger.logger.log(Level.INFO, logHeader + "Completed Recent World News Scrapping");
        return newsList;
    }

    public String returnToUSNewsHomePage(){
        webDriver.get(usNewsUrl);
        return webDriver.getCurrentUrl();}
    public String returnToPoliticsHomePage(){
        webDriver.get(politicsUrl);
        return webDriver.getCurrentUrl();
    }

    public String returnToWorldNewsHomePage(){
        webDriver.get(worldNewsUrl);
        return webDriver.getCurrentUrl();
    }

    private void performGetRecentPoliticsSetUp(){
        returnToPoliticsHomePage();
    }

    private void performGetRecentUSNewsSetUp(){
        returnToUSNewsHomePage();
    }
    private void performGetRecentWorldNewsSetUp(){returnToWorldNewsHomePage();}


    private List<NewsItem> performWebpageScrapping(){

        List<NewsItem> newsList = new ArrayList<>();

        WebElement articles = webDriver.findElement(By.className("jeg_block_container"));

        List<WebElement> articleList = articles.findElements(By.tagName("article"));
        for(WebElement w : articleList){
            WebElement newsContent = w.findElement(By.tagName("a"));
            String header = newsContent.findElement(By.tagName("img")).getAttribute("alt");
            String article = newsContent.getAttribute("href");
            String description = w.findElement(By.className("jeg_post_excerpt")).getText();
            String thumbnail = newsContent.findElement(By.tagName("img")).getAttribute("data-src");
            IndependentNewsItem item = new IndependentNewsItem(article,description,header, thumbnail);
            FileLogger.logger.log(Level.INFO, logHeader + "Scrapped item " + item);
            newsList.add(item);
        }

        return newsList;
    }

    public void quit(){
        webDriver.quit();
    }

}
