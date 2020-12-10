package com.manoranjan.newshunt1.Model;

import java.util.List;

public class CategoryData {
    private List<CategoryModel> category;

    public List<CategoryModel> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryModel> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ClassPojo [category = " + category + "]";
    }
}

