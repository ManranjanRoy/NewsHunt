package com.manoranjan.newshunt1.Datamodel;

import com.manoranjan.newshunt1.Model.CategoryModel;

import java.util.List;

public class SubCategoryData {
    private List<CategoryModel> sub_category;

    public List<CategoryModel> getCategory() {
        return sub_category;
    }

    public void setCategory(List<CategoryModel> category) {
        this.sub_category = category;
    }

    @Override
    public String toString() {
        return "ClassPojo [category = " + sub_category + "]";
    }
}

