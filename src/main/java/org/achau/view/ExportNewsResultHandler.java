package org.achau.view;

import org.achau.model.pojo.NewsItem;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides functionality for scrapped NewsList objects to output in a JSON format file
 * The JSON format file will be outputted in the location specificed in the "newsDirectoryPath" variable
 * @author Andrew Chau
 * @version 1.0
 */
public class ExportNewsResultHandler {

    public static final String newsDirectoryPath = "./News_results";

    /**
     * Checks if the newsDirectoryPath exists before the JSON format file is exported
     * A new directory is created with the given name if not found
     */
    private static void createNewsDirectory(){
        File newsDirectory = new File(newsDirectoryPath);
        if(!newsDirectory.exists()){
            newsDirectory.mkdirs();
        }
    }

    /**
     * Converts the NewsItem Object into a JSONObject
     * A serviceName and operation type must also be provided
     * @param newsList the list of scrapped NewsItems
     * @param serviceName the name of the website being scrapped
     * @param operation the operation for the website being scrapped
     * @return List of JSON NewsItem objects
     */
    private  static List<JSONObject> convertNewsListToJSON(List<NewsItem> newsList, String serviceName, String operation) {
        List<JSONObject> jsonNewsList = new ArrayList<>();

        for(var item : newsList){
            JSONObject jsonNewsItem = new JSONObject();
            jsonNewsItem.put("service", serviceName);
            jsonNewsItem.put("type", operation);
            jsonNewsItem.put("url",item.getUrl());
            jsonNewsItem.put("description",item.getDescription());
            jsonNewsItem.put("thumbnail",item.getThumbnailUrl());
            jsonNewsItem.put("title",item.getTitle());
            jsonNewsList.add(jsonNewsItem);
        }
        return jsonNewsList;
    }

    /**
     * Writes the converted JSON NewsItem List into a JSON file
     * @param jsonNewsList the list from the convertHNewsListToJSON method call
     * @param serviceName the name of the website being scrapped
     * @param operation the operation for the website being scrapped
     * @return the success or failure of the operation as a boolean
     */
    private static boolean writeNewsList(List<JSONObject> jsonNewsList, String serviceName, String operation){
        String fileName = serviceName + "_" + operation + ".json";
        try {
            FileWriter writer = new FileWriter(newsDirectoryPath + "/" + fileName);
            writer.write("[\n");
            for(int i = 0; i < jsonNewsList.size(); i++){
                var item = jsonNewsList.get(i);
                if (i + 1 != jsonNewsList.size()) { //Not last item. Comma is added to end of file
                    writer.write(item.toString() + ",\n");
                } else {
                    writer.write(item.toString());
                }

            }
            writer.write("]\n");
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Performs the JSON conversion of the newsList object and exports the JSON file in a given directory name
     * @param newsList the list of scrapped NewsItems
     * @param serviceName the name of the website being scrapped
     * @param operation the operation for the website being scrapped
     * @return the success or failure of the operation as a boolean
     */
    public static boolean exportNewsResults(List<NewsItem> newsList, String serviceName, String operation){
        List<JSONObject> jsonNewsList = convertNewsListToJSON(newsList, serviceName, operation);
        createNewsDirectory();
        return writeNewsList(jsonNewsList,serviceName,operation);
    }
}
