package com.example.myapplication;

public class SzallasItem{

    private String name;
    private String info;
    private String price;
    private final int imageResource;

    public SzallasItem(String name, String info, String price, int imageResource){
        this.name = name;
        this.info = info;
        this.price = price;
        this.imageResource = imageResource;
    }

    String getName(){return name;}
    String getInfo(){return info;}
    String getPrice(){return price;}
    public int getImageResource(){return imageResource;}

}
