package com.bd12024.BookStore.entities;

public class ReportByProductsType {
    public String productType;
    public int    totalAmount;
    public double totalSold;
    public double averagePrice;
    public double mostExpensiveProduct;
    public double cheapestProduct;

    public void addProductTypeReport(String productType, int totalAmount, double totalSold, double averagePrice, double mostExpensiveProduct, double cheapestProduct) {
        this.productType = productType;
        this.totalAmount = totalAmount;
        this.totalSold = totalSold;
        this.averagePrice = averagePrice;
        this.mostExpensiveProduct = mostExpensiveProduct;
        this.cheapestProduct = cheapestProduct;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(double totalSold) {
        this.totalSold = totalSold;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getMostExpensiveProduct() {
        return mostExpensiveProduct;
    }

    public void setMostExpensiveProduct(double mostExpensiveProduct) {
        this.mostExpensiveProduct = mostExpensiveProduct;
    }

    public double getCheapestProduct() {
        return cheapestProduct;
    }

    public void setCheapestProduct(double cheapestProduct) {
        this.cheapestProduct = cheapestProduct;
    }
}