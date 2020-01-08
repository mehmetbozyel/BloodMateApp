package com.example.bloodmateapp;


public class Post {
    public String name;
    public String bloodtype;
    public String city;
    public String hospital;
    public String details;
    public String time;
    public String phonenumber;
    public String user;

    public Post() {
    }

    public Post(String name, String bloodtype, String city, String hospital, String details, String time, String phonenumber, String user) {
        this.name = name;
        this.bloodtype = bloodtype;
        this.city = city;
        this.hospital = hospital;
        this.details = details;
        this.time = time;
        this.phonenumber = phonenumber;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}