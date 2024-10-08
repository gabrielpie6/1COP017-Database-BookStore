package com.bd12024.BookStore.dao;

import com.bd12024.BookStore.entities.Magazine;

import java.sql.SQLException;


public interface MagazineDAO extends DAO<Magazine> {

    public Magazine getByName(int prodCod) throws SQLException;
}