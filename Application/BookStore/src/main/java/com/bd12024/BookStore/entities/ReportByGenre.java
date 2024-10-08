package com.bd12024.BookStore.entities;

public class ReportByGenre {
    public String genre;
    public int    totalAmount;
    
    public void addGenreReport(String genre, int totalAmount) {
        this.genre = genre;
        this.totalAmount = totalAmount;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
