package com.bd12024.BookStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bd12024.BookStore.entities.ReportByProductsType;
import com.bd12024.BookStore.entities.ReportByGenre;
import com.bd12024.BookStore.entities.ReportByTheme;

public class PgReportDAO implements ReportDAO {
    private final Connection connection;

    public PgReportDAO(Connection connection) {
        this.connection = connection;
    }

    public static final String SELECT_BY_PRODUCT_TYPE =
            "SELECT " +
                "CASE " +
                    "WHEN b.fk_product_cod IS NOT NULL THEN 'Book' " +
                    "WHEN m.fk_product_cod IS NOT NULL THEN 'Magazine' " +
                    "WHEN p.fk_product_cod IS NOT NULL THEN 'Pagemark' " +
                "END AS productTypeString, " +
                "SUM(prod.amount) AS totalAmount, " + 
                "COALESCE(SUM(pp.checkout_amount * prod.price), 0) AS totalSold, " +
                "AVG(prod.price) AS averagePrice, " +
                "MAX(prod.price) AS mostExpensivePrice, " +
                "MIN(prod.price) AS cheapestPrice " +
            "FROM " + 
                "bookstore.product prod " +
            "LEFT JOIN " +
                "bookstore.book b ON prod.cod = b.fk_product_cod " +
            "LEFT JOIN " +
                "bookstore.magazine m ON prod.cod = m.fk_product_cod " +
            "LEFT JOIN " +
                "bookstore.pagemark p ON prod.cod = p.fk_product_cod " +
            "LEFT JOIN " +
                "bookstore.purchase_product pp ON prod.cod = pp.fk_product_cod " +
            "WHERE " +
                "b.fk_product_cod IS NOT NULL " +
                "OR m.fk_product_cod IS NOT NULL " +
                "OR p.fk_product_cod IS NOT NULL " +
            "GROUP BY " +
                "productTypeString;";

    public static final String SELECT_GENRE_RANKING =
            "SELECT " +
                "g.fname AS genreName, " +
                "SUM(p.amount) AS totalAmount " +
            "FROM " +
                "bookstore.book b " +
            "JOIN " +
                "bookstore.product p ON b.fk_product_cod = p.cod " +
            "JOIN " +
                "bookstore.genre g ON b.fk_genre_fname = g.fname " +
            "GROUP BY " +
                "g.fname " +
            "ORDER BY " +
                "totalAmount ASC;";
    
    public static final String SELECT_THEME_RANKING =
            "SELECT " +
                "t.fname AS themeName, " +
                "SUM(p.amount) AS totalAmount " +
            "FROM " +
                "bookstore.magazine m " +
            "JOIN " +
                "bookstore.product p ON m.fk_product_cod = p.cod " +
            "JOIN " +
                "bookstore.theme t ON m.fk_theme_fname = t.fname " +
            "GROUP BY " +
                "t.fname " +
            "ORDER BY " +
                "totalAmount ASC;";



    public List<ReportByProductsType> getReportByProductsType() throws SQLException
    {
        List<ReportByProductsType> reportsList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_PRODUCT_TYPE);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ReportByProductsType report = new ReportByProductsType();
                String productType          = rs.getString("productTypeString");
                int totalAmount             = rs.getInt("totalAmount");
                double totalSold            = rs.getDouble("totalSold");
                double averagePrice         = rs.getDouble("averagePrice");
                double mostExpensivePrice   = rs.getDouble("mostExpensivePrice");
                double cheapestPrice        = rs.getDouble("cheapestPrice");

                report.addProductTypeReport(productType, totalAmount, totalSold, averagePrice, mostExpensivePrice, cheapestPrice);
                reportsList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgReportDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao fazer relatório.");
        }
        return reportsList;
    }


    public List<ReportByGenre> getReportByGenres() throws SQLException {
        List<ReportByGenre> reportsList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_GENRE_RANKING);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ReportByGenre report = new ReportByGenre();
                String genreName     = rs.getString("genreName");
                int totalAmount      = rs.getInt("totalAmount");

                report.addGenreReport(genreName, totalAmount);
                reportsList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgReportDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao fazer relatório.");
        }
        return reportsList;
    }

    public List<ReportByTheme> getReportByThemes() throws SQLException {
        List<ReportByTheme> reportsList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_THEME_RANKING);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ReportByTheme report = new ReportByTheme();
                String themeName     = rs.getString("themeName");
                int totalAmount      = rs.getInt("totalAmount");

                report.addThemeReport(themeName, totalAmount);
                reportsList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgReportDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao fazer relatório.");
        }
        return reportsList;
    }
}
