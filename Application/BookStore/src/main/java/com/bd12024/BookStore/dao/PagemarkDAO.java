package com.bd12024.BookStore.dao;

import java.sql.SQLException;

import com.bd12024.BookStore.entities.Pagemark;

public interface PagemarkDAO extends DAO<Pagemark>{
    public Pagemark getByName(int cod) throws SQLException;
}
