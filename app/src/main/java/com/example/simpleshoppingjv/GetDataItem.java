package com.example.simpleshoppingjv;

public class GetDataItem {
    String title;   //검색 결과 문서 제목
    String link;    //검색 결과 링크
    String image;   //이미지 url
    int lprice;     //최저가 정보
  //  int hprice;   //최고가 정보
    String mallName;    //쇼핑몰 상호
    int protuctId;      //상품id
    int productType;    //상품 종류
    String maker;       //상품 제조사 명
    String brand;       //상품 브랜드 명
    String category1;   //대분류
    String category2;   //중분류
    String category3;   //소분류
    String category4;   //세분류

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
/*
    public int getHprice() {
        return hprice;
    }
*/
    public String getMallName() {
        return mallName;
    }

    public int getProtuctId() {
        return protuctId;
    }

    public int getProductType() {
        return productType;
    }

    public String getMaker() {
        return maker;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getCategory3() {
        return category3;
    }

    public String getCategory4() {
        return category4;
    }
}