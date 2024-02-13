package com.myBlog.myBlog;

public class Employee {
    private String name, city;
    private int age;

    //instead of setters using constructors
    public Employee(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    //getters

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }
}