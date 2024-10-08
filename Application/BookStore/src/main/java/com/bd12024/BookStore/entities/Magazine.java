package com.bd12024.BookStore.entities;

import java.sql.Date;

public class Magazine extends Product {
    public String ISSN;
    public String fname;
    public int pages;
    public Date publication_date;
    Publisher publisher;
    Theme theme;


    public Magazine() { }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }
    public void setPublication_date(String publication_date) {
        this.publication_date = Date.valueOf(publication_date);
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
