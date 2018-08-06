package model;

import javax.swing.*;
import java.sql.Timestamp;

public class Product {
    private int id;
    private String name;
    private String price;
    private String type;
    private int user_id;
    private String create_at;



    public Product(int id, String name, String price, String type, int user_id, String create_at) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.user_id = user_id;
        this.create_at = create_at;
    }

    public Product() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Product(String name, String price, String type, int user_id) {

        this.name = name;
        this.price = price;
        this.type = type;
        this.user_id = user_id;
    }

    public Product(int id, String name, String price, String type,String create_at) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.create_at = create_at;
    }
    public Product(int id, String name, String price, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}
