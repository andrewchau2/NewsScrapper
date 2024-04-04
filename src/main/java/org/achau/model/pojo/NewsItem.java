package org.achau.model.pojo;

public abstract class NewsItem {

    private String url;
        private String description;
        private String title;
        private String thumbnailUrl;
        public String getUrl(){
            return null;
    }
    public String getDescription(){
        return null;
    }
    public String getTitle(){
        return null;
    }

    public String getThumbnailUrl(){
        return null;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
