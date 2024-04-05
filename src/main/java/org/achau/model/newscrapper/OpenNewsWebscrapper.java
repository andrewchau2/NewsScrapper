package org.achau.model.newscrapper;

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

public class OpenNewsWebscrapper extends NewScrapper{

    private final String url;
    private final String description;

    private final WebDriver webDriver;

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

    @Override
    public String getUrl(){
        return url;
    }

    @Override
    public String getDescription(){
        return description;
    }

    public void returnToHomePage(){
        webDriver.get(url);
    }

    public List<NewsItem> getRecentNews(){
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));

        List<NewsItem> ns = wait.until(d -> {
            performGetRecentNewsSetup();
            return performGetRecentNewsExecution();
        });

        return ns;
    }
    private void performGetRecentNewsSetup(){
        returnToHomePage();
    }
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
            newsList.add(item);
        }
        return newsList;
    }
    public void quit(){
        webDriver.quit();
    }
}
