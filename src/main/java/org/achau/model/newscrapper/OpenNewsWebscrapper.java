package org.achau.model.newscrapper;

import org.achau.logger.FileLogger;
import org.achau.model.pojo.NewsItem;
import org.achau.model.pojo.OpenNewsItem;
import org.achau.model.util.StringTrimmer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Inherited class of NewScrapper that focuses on Open News Scrapping
 * All functionalities in this class defines the webscrapper for the Open News website
 * Results are outputted as a List of NewsItem objects.
 * Logging for the operations are also documented and outputted in the NewsScrapper_logs folder
 * @author Andrew Chau
 * @version 1.0
 */
public class OpenNewsWebscrapper extends NewScrapper{

    private final String url;
    private final String description;

    private final WebDriver webDriver;

    private final static String logHeader = "OpenNewsWebscrapper: ";

    /**
     * Constructor the the OpenNewsWebscrapper class.
     * The URL and description for the scrapping are initialized during this process.
     * Also the webscrapper is initialized along with any options given
     */
    public OpenNewsWebscrapper(){
       this.url =  "https://opennewswire.org/feed/?language=1";
       this.description = """
               Webscrapped News Articles from Open News Wire (https://opennewswire.org/)
               These webscrapped captures recent news articles along with a short description about them.
               For more info on the article, click on the link provided.
               """;
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-minimized");
        options.addArguments("--headless");
       this.webDriver = new EdgeDriver(options);
    }

    /**
     * Returns the OpenNews url initialized during the constructor operation
     * @return string of the url
     */
    @Override
    public String getUrl(){
        return url;
    }

    /**
     * Returns the OpenNews description initialized during the constructor operation
     * @return string of the description
     */
    @Override
    public String getDescription(){
        return description;
    }

    /**
     * Returns the webscrapper to the url provided
     */
    public void returnToHomePage(){
        webDriver.get(url);
    }

    /**
     * Returns the latest World News from scrapping
     * This method performs the execution of the scrapping
     * All necessary methods to get the scrapping results are called
     *
     * @return List of NewsItem Objects obtained from scrapping
     */
    public List<NewsItem> getRecentNews(){
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        FileLogger.logger.log(Level.INFO, logHeader + "Executing Recent World News Scrapping");
        List<NewsItem> ns = wait.until(d -> {
            performGetRecentNewsSetup();
            return performGetRecentNewsExecution();
        });

        FileLogger.logger.log(Level.INFO, logHeader + "Completed Recent World News Scrapping");
        return ns;
    }

    /**
     * Performs the setup of the Recent News webscrapping.
     * Any operations that need to be done before the webscrapper execution should be done in this method
     */
    private void performGetRecentNewsSetup(){
        returnToHomePage();
    }

    /**
     * Performs the execution of the Recent News webscrapping
     * This method will scrap all the recent news articles in the main page and output them in a List of NewsItem objects
     * @return a List of NewsItem objects obtained through the webscrapping
     */
    private List<NewsItem> performGetRecentNewsExecution(){
        List<NewsItem> newsList = new ArrayList<>();
        WebElement articlesList = webDriver.findElement(By.id("articles-list"));
        List<WebElement> articles = articlesList.findElements(By.className("ltr"));

        for (WebElement e : articles) {
            String href = e.findElement(By.tagName("a")).getAttribute("href");
            String title = e.findElement(By.tagName("a")).getText();
            String description = e.findElement(By.className("text-muted")).getAttribute("innerHTML");
            String descriptionTrimmed = description.trim().replaceAll("&nbsp;", "").replaceAll("\\s\\n","");
            descriptionTrimmed = StringTrimmer.trimToLength(descriptionTrimmed,1,150 );


            OpenNewsItem item = new OpenNewsItem(href,descriptionTrimmed,title);
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
