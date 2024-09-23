package com.bd12024.BookStore.entities;

public class Publisher {
    public String name;
    public String city;

    public Publisher(String name, String city) {
        this.name = name;
        this.city = city;
    }


    public Publisher() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
