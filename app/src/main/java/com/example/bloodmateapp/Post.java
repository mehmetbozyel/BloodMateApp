package com.example.bloodmateapp;

public class Post {
    public String name;
    public String bloodtype;
    public String city;
    public String hospital;
    public String details;
    public String time;

    public Post(String name, String bloodtype, String city, String hospital, String details, String time) {
        this.name = name;
        this.bloodtype = bloodtype;
        this.city = city;
        this.hospital = hospital;
        this.details = details;
        this.time = time;
    }
}