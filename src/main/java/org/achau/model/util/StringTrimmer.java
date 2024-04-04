package org.achau.model.util;

public class StringTrimmer {
    public static String trimToLength(String s,int startLength, int endLength){
        int stringLength = s.length();
        if(stringLength <= endLength)
            return s;

        return s.substring(startLength,endLength) + "...";
    }
}
