package com.example.simpleshoppingjv;

public class GetDataItem {
    String title;   //검색 결과 문서 제목
    String link;    //검색 결과 링크
    String image;   //이미지 url
    int lprice;     //최저가 정보

    public String getTitle() {
        return title.replace("<b>","").replace("</b>","");
    }

    public String getLink() {
        return changUrl(link);
    }

    public String changUrl(String link){
        int a = link.indexOf("=");
        String b = link.substring(a+1);
        return "https://msearch.shopping.naver.com/product/" + b ;
    }

    public String getImage() {
        return image;
    }

    public String getLprice() {
        return String.valueOf(lprice);
    }
}