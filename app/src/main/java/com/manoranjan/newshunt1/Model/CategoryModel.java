package com.manoranjan.newshunt1.Model;

public class CategoryModel {
    private String image;

    private String name;

    private String icon;

    private String description;

    private String id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClassPojo [image = " + image + ", name = " + name + ", icon = " + icon + ", description = " + description + ", id = " + id + "]";
    }
}
