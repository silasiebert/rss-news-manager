package br.udesc.argc.persistence.model;

/**
 * @author gabrielnaoto
 * @version 1.0
 * @created 10-mai-2017 19:51:30
 */
public class News {

    private int id;
    private String url;
    private String title;

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

    public void finalize() throws Throwable {

    }
}//end News
