package com.bd12024.BookStore.dao;

import com.bd12024.BookStore.entities.ReportByGenre;
import com.bd12024.BookStore.entities.ReportByProductsType;
import com.bd12024.BookStore.entities.ReportByTheme;

import java.sql.SQLException;
import java.util.List;

public interface ReportDAO {
    public List<ReportByProductsType>   getReportByProductsType() throws SQLException;
    public List<ReportByGenre>          getReportByGenres() throws SQLException;
    public List<ReportByTheme>          getReportByThemes() throws SQLException;
}
