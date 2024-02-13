package com.myBlog.myBlog;

public class Login {
    private String username, password;

    //instead of setters using constructors
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    } //constructor based injection to initialize username and password

    //getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
