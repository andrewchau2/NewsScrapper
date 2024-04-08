package org.achau.model.pojo;

/**
 * Abstract Class of the NewsItem Object. Each object should have an url path of the news link along with a title.
 * Description and Thumbnail may be left optional.
 * @author Andrew Chau
 * @version 1.0
 */
public abstract class NewsItem {

    private String url;
    private String description;
    private String title;
    private String thumbnailUrl;

    /**
     * Returns null for the News link url
     * Inherited methods should override this method
     * @return null
     */
    public String getUrl(){
            return null;
    }

    /**
     * Returns null for the News description
     * Inherited classes should override this method if description is present
     * @return null
     */
    public String getDescription(){
        return null;
    }

    /**
     * Returns null for the News title
     * Inherited classes should override this method title
     * @return null
     */
    public String getTitle(){
        return null;
    }

    /**
     * Returns null for the News thumbnail url
     * Inherited classes should override this method if thumbnail url is present
     * @return null
     */
    public String getThumbnailUrl(){
        return null;
    }


    /**
     * Returns contents of the NewsItem object in a string
     * @return string of the NewsItem Object
     */
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
