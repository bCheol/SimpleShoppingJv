package com.example.simpleshoppingjv;

public class ItemSearch {
    String title, link, image ;
    int lprice ;

    public ItemSearch(String title, String link, String image, int lprice) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.lprice = lprice;
    }

    public String getTitle() {
        return title.replace("<b>","").replace("</b>","");
    }

    public String getLink() {
        return link;
    }

    public String getLink2() {
        return changUrl(link);
    }

    public String getLink3() {
        return changUrl2(link);
    }

    public String getImage() {
        return image;
    }

    public String getLprice() {
        return String.valueOf(lprice);
    }

    public String changUrl(String link){
        int a = link.indexOf("=");
        String b = link.substring(a+1);
        return "https://msearch.shopping.naver.com/product/" + b ;
    }

    public String changUrl2(String link){
        return link.replace("search","msearch");
    }
}