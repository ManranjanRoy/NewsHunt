package com.manoranjan.newshunt1.Response;

import com.manoranjan.newshunt1.Datamodel.SubCategoryData;

public class SubCategoryResponse {
    private SubCategoryData data;

    private String error;

    private String message;

    public SubCategoryData getData() {
        return data;
    }

    public void setData(SubCategoryData data) {
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

