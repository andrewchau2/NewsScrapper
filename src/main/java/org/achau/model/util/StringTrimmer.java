package org.achau.model.util;

/**
 * Util class for News Objects
 * @version 1.0
 */
public class StringTrimmer {

    /**
     * Returns a Trimmed version of the original string.
     * @param s String of the sentence
     * @param startLength The start location of the sentence
     * @param endLength The end location of the sentence
     * @return string of the trimmed sentence
     */
    public static String trimToLength(String s,int startLength, int endLength){
        int stringLength = s.length();
        if(stringLength <= endLength)
            return s;

        return s.substring(startLength,endLength) + "...";
    }
}
