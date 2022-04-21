package com.example.music.view.login.entity;

public class User {
    private String userName;
    private String password;
    public Data data;

    public static class Data {
        public String photoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
