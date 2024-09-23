package com.bd12024.BookStore.dao;

import com.bd12024.BookStore.entities.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




public class PgThemeDAO implements ThemeDAO {

    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO bookstore.theme (fname, discount) " +
                    "VALUES(?, ?);";

    private static final String ALL_QUERY =
            "SELECT fname, discount " +
                    "FROM bookstore.theme " +
                    "ORDER BY fname ASC;";

    private static final String READ_QUERY =
            "SELECT fname, discount " +
                    "FROM bookstore.theme " +
                    "WHERE fname = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE bookstore.theme " +
                    "SET discount = ? " +
                    "WHERE fname = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bookstore.theme " +
                    "WHERE fname = ?;";

    private static final String COUNT_MAGAZINES_QUERY =
            "SELECT COUNT(*)" +
                    "FROM bookstore.magazine " +
                    "WHERE fk_theme_fname = ?;";

    public PgThemeDAO(Connection connection) {
        this.connection = connection;
    }



    @Override
    public Theme getByName(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public int countMagazinesByTheme(String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(COUNT_MAGAZINES_QUERY)) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("count");
                } else {
                    throw new SQLException("Erro ao visualizar: tema não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgThemeDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: tema não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar tema.");
            }
        }
    }

    @Override
    public void create(Theme theme) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, theme.getName());
            statement.setDouble(2, theme.getDiscount());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgThemeDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao inserir gênero: tema já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir tema: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir tema.");
            }
        }
    }

    @Override
    public Theme read(String name) throws SQLException {
        Theme theme = new Theme();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    theme.setName(name);
                    theme.setDiscount(result.getDouble("discount"));
                } else {
                    throw new SQLException("Erro ao visualizar: tema não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgThemeDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: tema não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar tema.");
            }
        }

        return theme;
    }

    @Override
    public void update(Theme theme) throws SQLException {
        String query;

        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, theme.getDiscount());
            statement.setString(2, theme.getName());


            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: tema não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgThemeDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: tema não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao editar tema: tema já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar tema: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar tema.");
            }
        }
    }

    @Override
    public void delete(String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, name);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: tema não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgThemeDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: tema não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir tema.");
            }
        }
    }

    @Override
    public List<Theme> all() throws SQLException {
        List<Theme> themeList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Theme theme = new Theme();
                theme.setName(result.getString("fname"));
                theme.setDiscount(result.getDouble("discount"));

                themeList.add(theme);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgThemeDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar temas.");
        }

        return themeList;
    }

}
