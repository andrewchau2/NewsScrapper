package org.achau.view;

import org.achau.model.pojo.NewsItem;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportNewsResultHandler {

    public static final String newsDirectoryPath = "./News_results";
    private static void createNewsDirectory(){
        File newsDirectory = new File(newsDirectoryPath);
        if(!newsDirectory.exists()){
            newsDirectory.mkdirs();
        }
    }
    private  static List<JSONObject> convertNewsListToJSON(List<NewsItem> newsList, String serviceName, String operation) {
        List<JSONObject> jsonNewsList = new ArrayList<>();

        for(var item : newsList){
            JSONObject jsonNewsItem = new JSONObject();
            jsonNewsItem.put("service", serviceName);
            jsonNewsItem.put("type", operation);
            jsonNewsItem.put("url",item.getUrl());
            System.out.println(item.getDescription());
            jsonNewsItem.put("description",item.getDescription());
            jsonNewsItem.put("thumbnail",item.getThumbnailUrl());
            jsonNewsItem.put("title",item.getTitle());
            jsonNewsList.add(jsonNewsItem);
        }
        return jsonNewsList;
    }

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

    public static boolean exportNewsResults(List<NewsItem> newsList, String serviceName, String operation){
        List<JSONObject> jsonNewsList = convertNewsListToJSON(newsList, serviceName, operation);
        createNewsDirectory();
        return writeNewsList(jsonNewsList,serviceName,operation);
    }
}
