package com.bd12024.BookStore.dao;

import java.sql.SQLException;
import com.bd12024.BookStore.entities.Theme;


public interface ThemeDAO extends DAO<Theme> {

    public Theme getByName(String name) throws SQLException;
    public int countMagazinesByTheme(String name) throws SQLException;
}