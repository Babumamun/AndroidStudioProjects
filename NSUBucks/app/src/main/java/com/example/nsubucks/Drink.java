package com.example.nsubucks;

import java.util.Arrays;

public class Drink {
    private int id;
    private String name;
    private String information;
    private String price;
    private byte[] ImgId;

    public Drink() {

    }

    public Drink(int id, String name, String information, String price, byte[] imgId) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.price = price;
        ImgId = imgId;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImgId() {
        return ImgId;
    }

    public void setImgId(byte[] imgId) {
        ImgId = imgId;
    }

//    @Override
//    public String toString() {
//        return "Drink{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", information='" + information + '\'' +
//                ", price='" + price + '\'' +
//                ", ImgId=" + Arrays.toString(ImgId) +
//                '}';
//    }
}