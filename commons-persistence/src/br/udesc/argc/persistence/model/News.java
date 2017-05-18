package br.udesc.argc.persistence.model;

import java.util.Date;

/**
 * @author gabrielnaoto
 * @version 1.0
 * @created 10-mai-2017 19:51:30
 */
public class News {

    private int id;
    private String url;
    private String title;
    private int feed;
    private Date date;

    public News() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFeed() {
        return feed;
    }

    public void setFeed(int feed) {
        this.feed = feed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", url=" + url + ", title=" + title + ", feed=" + feed + ", date=" + date + '}';
    }
    
    

    public void finalize() throws Throwable {

    }
}//end News
