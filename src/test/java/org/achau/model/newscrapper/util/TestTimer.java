package org.achau.model.newscrapper.util;

import java.util.Random;

public class TestTimer {
    public static void pauseRandomly(int min, int max){
        Random random = new Random();
        int value = random.nextInt((max - min) + min);
        try {
            Thread.sleep(value);
        }catch(Exception e){
            //Do nothing.
            e.printStackTrace();
        }
    }

}
