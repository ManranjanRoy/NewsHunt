package com.manoranjan.newshunt1.Response;

import com.manoranjan.newshunt1.Model.CategoryData;

public class CategoryResponse {
    private CategoryData data;

    private String error;

    private String message;

    public CategoryData getData() {
        return data;
    }

    public void setData(CategoryData data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClassPojo [data = " + data + ", error = " + error + ", message = " + message + "]";
    }
}

