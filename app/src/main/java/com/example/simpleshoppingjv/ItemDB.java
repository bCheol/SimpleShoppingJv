package com.example.simpleshoppingjv;

public class ItemDB {
    String title, link, image, lprice;

    public ItemDB(String title, String link, String image, String lprice) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.lprice = lprice;
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