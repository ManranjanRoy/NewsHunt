package com.manoranjan.newshunt1.Response;

import com.manoranjan.newshunt1.Model.LoginData;

public class LoginResponse {
    private LoginData data;

    private String error;

    private String message;

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
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
