package com.bd12024.BookStore.dao;


import com.bd12024.BookStore.entities.Publisher;

import java.sql.SQLException;


public interface PublisherDAO extends DAO<Publisher> {

    public Publisher getByName(String name) throws SQLException;
    public int countBooksByPublisher(String name) throws SQLException;
    public int countMagazinesByPublisher(String name) throws SQLException;
}