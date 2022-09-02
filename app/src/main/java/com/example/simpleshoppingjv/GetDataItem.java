package com.example.simpleshoppingjv;

public class GetDataItem {
    String title;   //검색 결과 문서 제목
    String link;    //검색 결과 링크
    String image;   //이미지 url
    int lprice;     //최저가 정보

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

   public String getImage() {
        return image;
    }

    public int getLprice() {
        return lprice;
    }
}