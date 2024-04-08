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

/**
 * Inherited class of NewScrapper that focuses on Independent News Scrapping
 * All functionalities in this class defines the webscrapper for the Independent News website
 * Results are outputted as a List of NewsItem objects.
 * Logging for the operations are also documented and outputted in the NewsScrapper_logs folder
 * @author Andrew Chau
 * @version 1.0
 */
public class IndependentNewsWebScrapper extends NewScrapper {

    private String url;
    private final WebDriver webDriver;
    private String description;

    public static final String politicsUrl = "https://ijr.com/tag/politics/";
    public static final String usNewsUrl = "https://ijr.com/tag/us-news/";

    public static final String worldNewsUrl = "https://ijr.com/tag/world-news/";

    private static final String logHeader = "IndependentNewsWebScrapper: ";

    /**
     * Constructor for the IndependentNewsWebScrapper class.
     * The URL and description for the scrapping are initialized during this process.
     * Also, the webscrapper is initialized along with any options given
     */
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

    /**
     * Returns the IndependentNews url initialized during the constructor operation
     * @return string of the url
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * Returns the IndependentNews description initialized during the constructor operation
     * @return string of the description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the latest Politics News from scrapping
     * This method performs the execution of the scrapping
     * All necessary methods to get the scrapping results are called
     *
     * @return List of NewsItem Objects obtained from scrapping
     */
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

    /**
     * Returns the latest US News from scrapping
     * This method performs the execution of the scrapping
     * All necessary methods to get the scrapping results are called
     *
     * @return List of NewsItem Objects obtained from scrapping
     */
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

    /**
     * Returns the latest World News from scrapping
     * This method performs the execution of the scrapping
     * All necessary methods to get the scrapping results are called
     *
     * @return List of NewsItem Objects obtained from scrapping
     */
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

    /**
     * Sets the webscrapper current page to the US News Page.
     * @return the string of the US News Page url
     */
    public String returnToUSNewsHomePage(){
        webDriver.get(usNewsUrl);
        return webDriver.getCurrentUrl();}
    /**
     * Sets the webscrapper current page to the Politics News Page.
     * @return the string of the Politics News Page url
     */
    public String returnToPoliticsHomePage(){
        webDriver.get(politicsUrl);
        return webDriver.getCurrentUrl();
    }

    /**
     * Sets the webscrapper current page to the World News Page.
     * @return the string of the World News Page url
     */
    public String returnToWorldNewsHomePage(){
        webDriver.get(worldNewsUrl);
        return webDriver.getCurrentUrl();
    }


    /**
     * Performs the setup of the Politics News webscrapping.
     * Any operations that need to be done before the webscrapper execution should be done in this method
     */
    private void performGetRecentPoliticsSetUp(){
        returnToPoliticsHomePage();
    }

    /**
     * Performs the setup of the US News webscrapping.
     * Any operations that need to be done before the webscrapper execution should be done in this method
     */
    private void performGetRecentUSNewsSetUp(){
        returnToUSNewsHomePage();
    }

    /**
     * Performs the setup of the World News webscrapping.
     * Any operations that need to be done before the webscrapper execution should be done in this method
     */
    private void performGetRecentWorldNewsSetUp(){returnToWorldNewsHomePage();}

    /**
     * Performs the webscrapping for all Independent News Article Pages
     * Regardless of the news article type scrapped(politics,US,etc), this method must always be called to get the results
     * @return list of NewsItems objects from scrapping
     */
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

    /**
     * Exits the web scrapper. No other operations from the class should be called after this method is called.
     */
    public void quit(){
        webDriver.quit();
    }

}
