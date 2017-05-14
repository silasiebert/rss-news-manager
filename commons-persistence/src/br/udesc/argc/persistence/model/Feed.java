package br.udesc.argc.persistence.model;

/**
 * @author gabrielnaoto
 * @version 1.0
 * @created 10-mai-2017 19:51:30
 */
public class Feed {

    private int id;
    private String name;
    private String url;

    public Feed() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void finalize() throws Throwable {

    }
}//end Site
