package com.bd12024.BookStore.entities;

public class ReportByTheme {
    public String theme;
    public int    totalAmount;
    
    public void addThemeReport(String theme, int totalAmount) {
        this.theme = theme;
        this.totalAmount = totalAmount;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
