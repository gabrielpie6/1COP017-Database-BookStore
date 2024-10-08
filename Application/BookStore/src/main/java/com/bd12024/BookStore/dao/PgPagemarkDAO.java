package com.bd12024.BookStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bd12024.BookStore.entities.Pagemark;

public class PgPagemarkDAO implements PagemarkDAO{
    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO bookstore.pagemark (fk_product_cod, color) " +
            "VALUES(?, ?);";

    private static final String ALL_QUERY =
            "SELECT p.cod, p.price, p.amount, m.color " +
            "FROM bookstore.product AS p JOIN bookstore.pagemark AS m ON p.cod = m.fk_product_cod " +
            "ORDER BY p.cod ASC;";

    private static final String READ_QUERY =
            "SELECT p.cod, p.price, p.amount, m.color " +
            "FROM bookstore.product AS p JOIN bookstore.pagemark AS m ON p.cod = m.fk_product_cod " +
            "WHERE p.cod = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE bookstore.pagemark " +
            "SET color = ? " +
            "WHERE fk_product_cod = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bookstore.pagemark " +
            "WHERE fk_product_cod = ?;";
    
    private static final String CREATE_PRODUCT_QUERY =
            "INSERT INTO bookstore.product (cod, price, amount) " +
            "VALUES(?, ?, ?);";




    public PgPagemarkDAO(Connection connection) {
        this.connection = connection;
    }



    @Override
    public Pagemark getByName(int cod) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public void create(Pagemark pagemark) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
             // Creating Product to be associated with Pagemark
             try (PreparedStatement statementProd = connection.prepareStatement(CREATE_PRODUCT_QUERY)) {                
                statementProd.setInt(       1, pagemark.getCod());
                statementProd.setDouble(    2, pagemark.getPrice());
                statementProd.setInt(       3, pagemark.getAmount());
    
                statementProd.executeUpdate();
            } catch (SQLException exProd) {
                Logger.getLogger(PgPagemarkDAO.class.getName()).log(Level.SEVERE, "DAO", exProd);
    
                if (exProd.getMessage().contains("uq_user_login")) {
                    throw new SQLException("Erro ao inserir marcador: produto já existente.");
                } else if (exProd.getMessage().contains("not-null")) {
                    throw new SQLException("Erro ao inserir marcador: pelo menos um campo está em branco.");
                } else {
                    throw new SQLException("Erro ao inserir marcador.");
                }
            }



            statement.setInt(       1, pagemark.getCod());
            statement.setString(    2, pagemark.getColor());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgPagemarkDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao inserir marcador: marcador já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir marcador: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir marcador.");
            }
        }
    }

    @Override
    public Pagemark read(int cod) throws SQLException {
        Pagemark pagemark = new Pagemark();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, cod);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    pagemark.setCod(                cod);
                    pagemark.setPrice(              result.getDouble("price"));
                    pagemark.setAmount(             result.getInt("amount"));

                    pagemark.setColor(              result.getString("color"));
                } else {
                    throw new SQLException("Erro ao visualizar: marcador não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPagemarkDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: marcador não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar marcador.");
            }
        }

        return pagemark;
    }

    @Override
    public void update(Pagemark pagemark) throws SQLException {
        String query;

        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(    1, pagemark.getColor());
            statement.setInt(       2, pagemark.getCod());


            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: marcador não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPagemarkDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: marcador não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao editar marcador: marcador já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar marcador: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar marcador.");
            }
        }
    }

    @Override
    public void delete(int cod) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, cod);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: marcador não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPagemarkDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: marcador não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir marcador.");
            }
        }
    }

    @Override
    public List<Pagemark> all() throws SQLException {
        List<Pagemark> pagemarksList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Pagemark pagemark = new Pagemark();

                pagemark.setCod(                result.getInt("cod"));
                pagemark.setPrice(              result.getDouble("price"));
                pagemark.setAmount(             result.getInt("amount"));

                pagemark.setColor(              result.getString("color"));

                pagemarksList.add(pagemark);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPagemarkDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar marcadores.");
        }

        return pagemarksList;
    }
}
