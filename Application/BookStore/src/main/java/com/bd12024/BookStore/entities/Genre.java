package com.bd12024.BookStore.entities;

public class Genre {
    public String name;
    public double discount;

    public Genre(String name, double discount) {
        this.name = name;
        this.discount = discount;
    }

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getFormattedDiscount()
    {
        return String.format("%.2f %%", this.discount * 100.0);
    }
}
