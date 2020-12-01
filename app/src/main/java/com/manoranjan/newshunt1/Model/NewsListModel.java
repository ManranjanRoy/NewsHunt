package com.manoranjan.newshunt1.Model;

public class NewsListModel {

    String id,name,desc,pprice,oprice,discount,wishlist;
    int img;

    public NewsListModel() {
    }

    public NewsListModel(String id, String name, String desc, String pprice, String oprice, String discount, String wishlist, int img) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.pprice = pprice;
        this.oprice = oprice;
        this.discount = discount;
        this.wishlist = wishlist;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getOprice() {
        return oprice;
    }

    public void setOprice(String oprice) {
        this.oprice = oprice;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }
}
