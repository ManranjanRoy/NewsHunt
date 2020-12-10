package com.manoranjan.newshunt1.Model;

public class User_data {
    private String name;

    private String id;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClassPojo [name = " + name + ", id = " + id + ", email = " + email + "]";
    }
}

