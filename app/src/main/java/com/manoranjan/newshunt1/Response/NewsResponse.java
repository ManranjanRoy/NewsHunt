package com.manoranjan.newshunt1.Response;

import com.manoranjan.newshunt1.Model.NewsData;

public class NewsResponse {
    private NewsData data;

    private String error;

    private String message;

    public NewsData getData() {
        return data;
    }

    public void setData(NewsData data) {
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