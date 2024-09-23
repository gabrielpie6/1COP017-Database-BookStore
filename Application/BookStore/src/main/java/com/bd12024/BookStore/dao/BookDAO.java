package com.bd12024.BookStore.dao;

import com.bd12024.BookStore.entities.Book;

import java.sql.SQLException;


public interface BookDAO extends DAO<Book> {

    public Book getByName(int prodCod) throws SQLException;
}