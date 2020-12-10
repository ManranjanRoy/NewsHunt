package com.manoranjan.newshunt1.Model;

public class LoginData {
    private User_data user_data;

    private String token;

    public User_data getUser_data() {
        return user_data;
    }

    public void setUser_data(User_data user_data) {
        this.user_data = user_data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ClassPojo [user_data = " + user_data + ", token = " + token + "]";
    }
}


