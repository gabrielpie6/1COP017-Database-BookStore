package com.bd12024.BookStore.dao;

import java.sql.SQLException;
import com.bd12024.BookStore.entities.Genre;


public interface GenreDAO extends DAO<Genre> {

    public Genre getByName(String genreName) throws SQLException;
    public int countBooksByGenre(String genreName) throws SQLException;
}