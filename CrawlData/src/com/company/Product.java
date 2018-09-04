package com.company;

public class Product {
    private String name;
    private String url;
    private int price;
    private String type;

    public Product(String name, int price, String url, String type) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.type = type;
    }

    public Product(String name, int price, String url) {
        this.name = name;
        this.url = url;
        this.price = price;
    }

    public Product() {

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
