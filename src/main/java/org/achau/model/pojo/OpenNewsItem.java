package org.achau.model.pojo;

public class OpenNewsItem extends NewsItem{
    private final String url;
    private final String description;
    private final String title;


    public OpenNewsItem(String url, String description,String title){
        this.url = url;
        this.description = description;
        this.title = title;
    }

    @Override
    public String getUrl(){
        return url;
    }
    @Override
    public String getDescription(){
        return description;
    }
    @Override
    public String getTitle(){
        return title;
    }

    @Override
    public String toString() {
        return "OpenNewsItem{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
