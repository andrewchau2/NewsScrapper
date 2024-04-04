package org.achau.model.newscrapper;


import org.achau.model.newscrapper.util.TestTimer;
import org.achau.model.pojo.NewsItem;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OpenNewsWebscrapperTest {

    private static final OpenNewsWebscrapper ns = new OpenNewsWebscrapper();


    @BeforeEach
    void delayCall(){
        TestTimer.pauseRandomly(1000,5000);
    }


    @Test
    @DisplayName("Open News Page Test")
    void testGetUrlShouldBeOpenNewsUrl(){
        assertEquals(ns.getUrl(), "https://opennewswire.org/feed/?language=1");
    }

    @Test
    @DisplayName("Open News Scrapping Test")
    void testWebScrappingExecutionShouldNotBeNull(){
        List<NewsItem> newsList = ns.getRecentNews();
        assertFalse(newsList.isEmpty());
    }

    @AfterAll
    static void cleanUp(){
        ns.quit();
    }
}
