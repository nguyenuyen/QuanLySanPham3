package model;

import java.sql.Timestamp;
import java.util.Date;

public class User_log {
    private  int id;
    private String user;
    private Timestamp time;
    private  String type;



    public User_log( String user, Timestamp time, String type) {
        this.user = user;
        this.time = time;
        this.type = type;
    }
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
