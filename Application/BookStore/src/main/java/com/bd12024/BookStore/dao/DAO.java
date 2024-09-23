package com.bd12024.BookStore.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dskaster & pie
 * @param <T>
 */
public interface DAO<T> {

    public void create(T t) throws SQLException;

    default T read(String id) throws SQLException {
        return null;
    }

    default T read(int id) throws SQLException {
        return null;
    }

    public void update(T t) throws SQLException;
    default void delete(String id) throws SQLException { };

    default void delete(int id) throws SQLException { }

    public List<T> all() throws SQLException;

}
