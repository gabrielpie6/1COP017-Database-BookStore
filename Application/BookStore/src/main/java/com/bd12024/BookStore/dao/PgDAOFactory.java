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
    public PublisherDAO getPublisherDAO() { return new PgPublisherDAO(this.connection); }

    @Override
    public BookDAO getBookDAO() {
        return new PgBookDAO(this.connection);
    }

    @Override
    public MagazineDAO getMagazineDAO() {
        return new PgMagazineDAO(this.connection);
    }

    @Override
    public PagemarkDAO getPagemarkDAO() {
        return new PgPagemarkDAO(this.connection);
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        return new PgCustomerDAO(this.connection);
    }

    @Override
    public ReportDAO getReportDAO() {
        return new PgReportDAO(this.connection);
    }
}