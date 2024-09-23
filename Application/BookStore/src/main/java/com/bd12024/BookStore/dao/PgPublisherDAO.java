package com.bd12024.BookStore.dao;

import com.bd12024.BookStore.entities.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PgPublisherDAO implements PublisherDAO {

    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO bookstore.publisher (fname, city) " +
            "VALUES(?, ?);";

    private static final String ALL_QUERY =
            "SELECT fname, city " +
            "FROM bookstore.publisher " +
            "ORDER BY fname ASC;";

    private static final String READ_QUERY =
            "SELECT fname, city " +
            "FROM bookstore.publisher " +
            "WHERE fname = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE bookstore.publisher " +
            "SET city = ? " +
            "WHERE fname = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bookstore.publisher " +
            "WHERE fname = ?;";

    private static final String COUNT_BOOKS_QUERY =
            "SELECT COUNT(*)" +
            "FROM bookstore.book " +
            "WHERE fk_publisher_fname = ?;";
    private static final String COUNT_MAGAZINES_QUERY =
            "SELECT COUNT(*)" +
            "FROM bookstore.magazine " +
            "WHERE fk_publisher_fname = ?;";

    public PgPublisherDAO(Connection connection) {
        this.connection = connection;
    }



    @Override
    public Publisher getByName(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public int countBooksByPublisher(String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(COUNT_BOOKS_QUERY)) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("count");
                } else {
                    throw new SQLException("Erro ao visualizar: editora não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPublisherDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: editora não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar editora.");
            }
        }
    }
    @Override
    public int countMagazinesByPublisher(String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(COUNT_MAGAZINES_QUERY)) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("count");
                } else {
                    throw new SQLException("Erro ao visualizar: editora não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPublisherDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: editora não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar editora.");
            }
        }
    }

    @Override
    public void create(Publisher publisher) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, publisher.getName());
            statement.setString(2, publisher.getCity());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgPublisherDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao inserir editora: editora já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir editora: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir editora.");
            }
        }
    }

    @Override
    public Publisher read(String name) throws SQLException {
        Publisher publisher = new Publisher();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    publisher.setName(name);
                    publisher.setCity(result.getString("city"));
                } else {
                    throw new SQLException("Erro ao visualizar: editora não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPublisherDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: editora não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar editora.");
            }
        }

        return publisher;
    }

    @Override
    public void update(Publisher publisher) throws SQLException {
        String query;

        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, publisher.getCity());
            statement.setString(2, publisher.getName());


            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: editora não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPublisherDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: editora não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao editar editora: editora já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar editora: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar editora.");
            }
        }
    }

    @Override
    public void delete(String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, name);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: editora não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPublisherDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: editora não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir editora.");
            }
        }
    }

    @Override
    public List<Publisher> all() throws SQLException {
        List<Publisher> publisherList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Publisher publisher = new Publisher();
                publisher.setName(result.getString("fname"));
                publisher.setCity(result.getString("city"));

                publisherList.add(publisher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPublisherDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar editoras.");
        }

        return publisherList;
    }

}
