package com.bd12024.BookStore.entities;

public class Book extends Product {
    public int ISBN;
    public String title;
    public int edition;
    public String language;
    public int pages;
    public int publication_year;
    public Publisher publisher;
    public Genre genre;

    public Book(int cod, double price, int amount, int ISBN, String title, int edition, String language, int pages, int publication_year, Publisher publisher, Genre genre) {
        this.cod = cod;
        this.price = price;
        this.amount = amount;

        this.ISBN = ISBN;
        this.title = title;
        this.edition = edition;
        this.language = language;
        this.pages = pages;
        this.publication_year = publication_year;
        this.publisher = publisher;
        this.genre = genre;
    }

    public Book() { }


}
