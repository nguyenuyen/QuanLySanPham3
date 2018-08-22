package model;

import java.sql.Timestamp;

public class Image {
    private  int id;
    private String url;
    private  String create_at;

    public Image(int id, String url, String create_at) {
        this.id = id;
        this.url = url;
        this.create_at = create_at;
    }

    public Image(int id, String url) {
        this.id = id;
        this.url = url;
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

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}
