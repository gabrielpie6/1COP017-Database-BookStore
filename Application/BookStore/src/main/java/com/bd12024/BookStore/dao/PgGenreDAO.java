package com.bd12024.BookStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bd12024.BookStore.entities.Genre;


public class PgGenreDAO implements GenreDAO {

    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO bookstore.genre (fname, discount) " +
            "VALUES(?, ?);";

    private static final String ALL_QUERY =
            "SELECT fname, discount " +
            "FROM bookstore.genre " +
            "ORDER BY fname ASC;";

    private static final String READ_QUERY =
            "SELECT fname, discount " +
            "FROM bookstore.genre " +
            "WHERE fname = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE bookstore.genre " +
            "SET discount = ? " +
            "WHERE fname = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bookstore.genre " +
            "WHERE fname = ?;";

    private static final String COUNT_BOOKS_QUERY =
            "SELECT COUNT(*)" +
            "FROM bookstore.book " +
            "WHERE fk_genre_fname = ?;";

    public PgGenreDAO(Connection connection) {
        this.connection = connection;
    }



    @Override
    public Genre getByName(String genreName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public int countBooksByGenre(String genreName) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(COUNT_BOOKS_QUERY)) {
            statement.setString(1, genreName);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("count");
                } else {
                    throw new SQLException("Erro ao visualizar: gênero não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgGenreDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: gênero não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar gênero.");
            }
        }
    }

    @Override
    public void create(Genre genre) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, genre.getName());
            statement.setDouble(2, genre.getDiscount());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgGenreDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao inserir gênero: gênero já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir gênero: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir gênero.");
            }
        }
    }

    @Override
    public Genre read(String name) throws SQLException {
        Genre genre = new Genre();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    genre.setName(name);
                    genre.setDiscount(result.getDouble("discount"));
                } else {
                    throw new SQLException("Erro ao visualizar: gênero não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgGenreDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: gênero não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar gênero.");
            }
        }

        return genre;
    }

    @Override
    public void update(Genre genre) throws SQLException {
        String query;

        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, genre.getDiscount());
            statement.setString(2, genre.getName());


            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: gênero não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgGenreDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: gênero não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao editar gênero: gênero já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar gênero: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar gênero.");
            }
        }
    }

    @Override
    public void delete(String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, name);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: gênero não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgGenreDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: gênero não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir gênero.");
            }
        }
    }

    @Override
    public List<Genre> all() throws SQLException {
        List<Genre> genreList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Genre genre = new Genre();
                genre.setName(result.getString("fname"));
                genre.setDiscount(result.getDouble("discount"));

                genreList.add(genre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgGenreDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar gêneros.");
        }

        return genreList;
    }

}
