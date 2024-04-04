package org.achau.model.newscrapper;

import org.achau.model.newscrapper.util.TestTimer;
import org.achau.model.pojo.IndependentNewsItem;
import org.achau.model.pojo.NewsItem;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IndependentNewsWebScrapperTest {

    private static IndependentNewsWebScrapper webScrapper;

    @BeforeAll
    static void setUp(){
        webScrapper = new IndependentNewsWebScrapper();
    }

    @BeforeEach
    void delayCall(){
        TestTimer.pauseRandomly(0,4000);
    }

    @Test
    @DisplayName("Indepedent News Main Page Test")
    void getIndependentNewsUrlTestShouldBeEqual(){
        assertEquals(webScrapper.getUrl(), "https://ijr.com/");
    }


    @Test
    @DisplayName("Independent Journal Politics Page Test")
    void getPoliticsUrlTestShouldBeEqual(){
        assertEquals(webScrapper.returnToPoliticsHomePage(), "https://ijr.com/tag/politics/");
    }

    @Test
    @DisplayName("Independent Journal US News Page Test")
    void getUSNewsUrlTestShouldBeEqual(){
        assertEquals(webScrapper.returnToUSNewsHomePage(),"https://ijr.com/tag/us-news/");
    }

    @Test
    @DisplayName("Independent Journal World News Page Test")
    void getWorldNewsUrlTestShouldBeEqual(){
        assertEquals(webScrapper.returnToWorldNewsHomePage(),"https://ijr.com/tag/world-news/");
    }


    @Test
    @DisplayName("Independent Journal Politics New Scrapping Test")
    void getRecentPoliticsNewsTestShouldBeTrue(){
        List<NewsItem> newsList = webScrapper.getRecentPoliticsNews();
        assertFalse(newsList.isEmpty());

        IndependentNewsItem item = (IndependentNewsItem) newsList.getLast();
        assertNotNull(item.getUrl());
        assertNotNull(item.getDescription());
        assertNotNull(item.getTitle());
        assertNotNull(item.getThumbnailUrl());
    }

    @Test
    @DisplayName("Independent Journal US News Scrapping Test")
    void getRecentUSNewsTestShouldBeTrue(){
        List<NewsItem> newsList = webScrapper.getRecentPoliticsNews();
        assertFalse(newsList.isEmpty());

        IndependentNewsItem item = (IndependentNewsItem) newsList.getLast();
        assertNotNull(item.getUrl());
        assertNotNull(item.getDescription());
        assertNotNull(item.getTitle());
        assertNotNull(item.getThumbnailUrl());
    }

    @Test
    @DisplayName("Independent Journal World News Scrapping Test")
    void getRecentWorldNewsShouldBeTrue(){
        List<NewsItem> newsList = webScrapper.getRecentPoliticsNews();
        assertFalse(newsList.isEmpty());

        IndependentNewsItem item = (IndependentNewsItem) newsList.getLast();
        assertNotNull(item.getUrl());
        assertNotNull(item.getDescription());
        assertNotNull(item.getTitle());
        assertNotNull(item.getThumbnailUrl());

    }



    @AfterAll
    static void cleanUp(){
        webScrapper.quit();
    }



}
