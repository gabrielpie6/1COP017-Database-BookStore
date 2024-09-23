package com.bd12024.BookStore.dao;

import java.sql.Connection;


public class PgDAOFactory extends DAOFactory {

    public PgDAOFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public GenreDAO getGenreDAO() {
        return new PgGenreDAO(this.connection);
    }

    @Override
    public ThemeDAO getThemeDAO() {
        return new PgThemeDAO(this.connection);
    }

    @Override
    public PublisherDAO getPublisherDAO() {
        return new PgPublisherDAO(this.connection);
    }
}