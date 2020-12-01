package com.manoranjan.newshunt1.Model;

public class Color {
    private String id;

    private String product_id;

    private int color;

    private  boolean isselected;

    public Color(String id, String product_id, int color) {
        this.id = id;
        this.product_id = product_id;
        this.color = color;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setProduct_id(String product_id){
        this.product_id = product_id;
    }
    public String getProduct_id(){
        return this.product_id;
    }
    public void setColor(int color){
        this.color = color;
    }
    public int getColor(){
        return this.color;
    }
}
