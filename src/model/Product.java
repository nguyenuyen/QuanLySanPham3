package model;

import javax.swing.*;

public class Product {
    private int id;
    private String name;
    private int price;
    private String type;
    private int user_id;

    public Product(int id, String name, int price, String type, int user_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.user_id = user_id;
    }

    public Product() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Product(String name, int price, String type, int user_id) {

        this.name = name;
        this.price = price;
        this.type = type;
        this.user_id = user_id;
    }

    public Product(int id, String name, int price, String type) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
