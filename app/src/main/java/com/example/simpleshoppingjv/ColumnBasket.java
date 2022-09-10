package com.example.simpleshoppingjv;

public class ColumnBasket {
    int id;
    String title, link, image, lprice;

    public ColumnBasket(int id, String title, String link, String image, String lprice) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.image = image;
        this.lprice = lprice;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public String getLprice() {
        return lprice;
    }
}
