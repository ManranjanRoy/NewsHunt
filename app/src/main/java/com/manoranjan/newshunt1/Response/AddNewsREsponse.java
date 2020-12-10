package com.manoranjan.newshunt1.Response;

public class AddNewsREsponse {


    private String error;

    private String message;


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
        return "ClassPojo [data = " + ", error = " + error + ", message = " + message + "]";
    }
}

