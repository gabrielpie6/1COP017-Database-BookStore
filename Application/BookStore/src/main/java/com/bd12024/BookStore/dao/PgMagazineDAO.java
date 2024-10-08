package com.bd12024.BookStore.dao;

import com.bd12024.BookStore.entities.Magazine;
import com.bd12024.BookStore.entities.Theme;
import com.bd12024.BookStore.entities.Publisher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgMagazineDAO implements MagazineDAO {

    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO bookstore.magazine (fk_product_cod, ISSN, fname, pages, publication_date, fk_publisher_fname, fk_theme_fname) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?);";

    private static final String ALL_QUERY =
            "SELECT p.cod, p.price, p.amount, m.ISSN, m.fname, m.pages, m.publication_date, m.fk_publisher_fname, m.fk_theme_fname " +
            "FROM bookstore.product AS p JOIN bookstore.magazine AS m ON p.cod = m.fk_product_cod " +
            "ORDER BY p.cod ASC;";

    private static final String READ_QUERY =
            "SELECT p.cod, p.price, p.amount, m.ISSN, m.fname, m.pages, m.publication_date, m.fk_publisher_fname, m.fk_theme_fname " +
            "FROM bookstore.product AS p JOIN bookstore.magazine AS m ON p.cod = m.fk_product_cod " +
            "WHERE p.cod = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE bookstore.magazine " +
            "SET ISSN = ?, fname = ?, pages = ?, publication_date = ?, fk_publisher_fname = ?, fk_theme_fname = ? " +
            "WHERE fk_product_cod = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bookstore.magazine " +
            "WHERE fk_product_cod = ?;";

    private static final String CREATE_PRODUCT_QUERY =
            "INSERT INTO bookstore.product (cod, price, amount) " +
            "VALUES(?, ?, ?);";

    public PgMagazineDAO(Connection connection) {
        this.connection = connection;
    }



    @Override
    public Magazine getByName(int prodCod) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }




    @Override
    public void create(Magazine magazine) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            // Creating Product to be associated with Magazine
            try (PreparedStatement statementProd = connection.prepareStatement(CREATE_PRODUCT_QUERY)) {                
                statementProd.setInt(       1, magazine.getCod());
                statementProd.setDouble(    2, magazine.getPrice());
                statementProd.setInt(       3, magazine.getAmount());
    
                statementProd.executeUpdate();
            } catch (SQLException exProd) {
                Logger.getLogger(PgMagazineDAO.class.getName()).log(Level.SEVERE, "DAO", exProd);
    
                if (exProd.getMessage().contains("uq_user_login")) {
                    throw new SQLException("Erro ao inserir revista: produto já existente.");
                } else if (exProd.getMessage().contains("not-null")) {
                    throw new SQLException("Erro ao inserir revista: pelo menos um campo está em branco.");
                } else {
                    throw new SQLException("Erro ao inserir revista.");
                }
            }



            statement.setInt(       1, magazine.getCod());
            statement.setString(    2, magazine.getISSN());
            statement.setString(    3, magazine.getFname());
            statement.setInt(       4, magazine.getPages());
            statement.setDate(      5, magazine.getPublication_date());
            statement.setString(    6, magazine.getPublisher().getName());
            statement.setString(    7, magazine.getTheme().getName());


            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgMagazineDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao inserir revista: revista já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir revista: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir revista.");
            }
        }
    }

    @Override
    public Magazine read(int prodCod) throws SQLException {
        Magazine magazine = new Magazine();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, prodCod);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    // Product attributes
                    magazine.setCod(                prodCod);
                    magazine.setPrice(              result.getDouble("price"));
                    magazine.setAmount(             result.getInt("amount"));
                    // Magazine attributes
                    magazine.setISSN(               result.getString("ISSN"));
                    magazine.setFname(              result.getString("fname"));
                    magazine.setPages(              result.getInt("pages"));
                    magazine.setPublication_date(   result.getDate("publication_date"));


                    PublisherDAO daoPub;
                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        daoPub = daoFactory.getPublisherDAO();
                        magazine.setPublisher(  daoPub.read(result.getString("fk_publisher_fname")) );

                    } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                        System.out.println(ex.getMessage());
                        magazine.setPublisher(new Publisher("NOT_FOUND", "NOT_FOUND") );
                    }

                    ThemeDAO daoTheme;
                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        daoTheme = daoFactory.getThemeDAO();
                        magazine.setTheme(  daoTheme.read(result.getString("fk_theme_fname"))  );

                    } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                        System.out.println(ex.getMessage());
                        magazine.setTheme(new Theme("NOT_FOUND", 0.0) );
                    }
                } else {
                    throw new SQLException("Erro ao visualizar: revista não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgMagazineDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: revista não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar revista.");
            }
        }

        return magazine;
    }

    @Override
    public void update(Magazine magazine) throws SQLException {
        String query;

        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(    1, magazine.getISSN());
            statement.setString(    2, magazine.getFname());
            statement.setInt(       3, magazine.getPages());
            statement.setDate(      4, magazine.getPublication_date());
            statement.setString(    5, magazine.getPublisher().getName());
            statement.setString(    6, magazine.getTheme().getName());
            statement.setInt(       7, magazine.getCod());


            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: revista não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgMagazineDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: revistra não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao editar revista: revista já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar revista: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar revista.");
            }
        }
    }

    @Override
    public void delete(int prodCod) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, prodCod);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: revista não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgMagazineDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: revista não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir revista.");
            }
        }
    }

    @Override
    public List<Magazine> all() throws SQLException {
        List<Magazine> magazineList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Magazine magazine = new Magazine();
                magazine.setCod(                result.getInt("cod"));
                magazine.setPrice(              result.getDouble("price"));
                magazine.setAmount(             result.getInt("amount"));
                magazine.setISSN(               result.getString("ISSN"));
                magazine.setFname(              result.getString("fname"));
                magazine.setPages(              result.getInt("pages"));
                magazine.setPublication_date(   result.getDate("publication_date"));

                PublisherDAO daoPub;
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    daoPub = daoFactory.getPublisherDAO();
                    magazine.setPublisher(  daoPub.read(result.getString("fk_publisher_fname")) );

                } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                    System.out.println(ex.getMessage());
                    magazine.setPublisher(new Publisher("NOT_FOUND", "NOT_FOUND") );
                }

                ThemeDAO daoTheme;
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    daoTheme = daoFactory.getThemeDAO();
                    magazine.setTheme(  daoTheme.read(result.getString("fk_theme_fname"))  );

                } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                    System.out.println(ex.getMessage());
                    magazine.setTheme(new Theme("NOT_FOUND", 0.0) );
                }

                magazineList.add(magazine);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgMagazineDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar revistas.");
        }

        return magazineList;
    }
}
