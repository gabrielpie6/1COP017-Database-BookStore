package com.bd12024.BookStore.dao;

import com.bd12024.BookStore.entities.Book;
import com.bd12024.BookStore.entities.Genre;
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


public class PgBookDAO implements BookDAO {

    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO bookstore.book (fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String ALL_QUERY =
            "SELECT fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname " +
            "FROM bookstore.book " +
            "ORDER BY fk_product_cod ASC;";

    private static final String READ_QUERY =
            "SELECT fk_product_cod, ISBN, title, bedition, blanguage, pages, publication_year, fk_publisher_fname, fk_genre_fname " +
            "FROM bookstore.book " +
            "WHERE fk_product_cod = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE bookstore.book " +
            "SET ISBN = ?, title = ?, bedition = ?, blanguage = ?, pages = ?, publication_year = ?, fk_publisher_fname = ?, fk_genre_fname = ? " +
            "WHERE fk_product_cod = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM bookstore.book " +
            "WHERE fk_product_cod = ?;";


    public PgBookDAO(Connection connection) {
        this.connection = connection;
    }



    @Override
    public Book getByName(int prodCod) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }




    @Override
    public void create(Book book) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setInt(       1, book.getCod());
            statement.setString(    2, book.getISBN());
            statement.setString(    3, book.getTitle());
            statement.setInt(       4, book.getEdition());
            statement.setString(    5, book.getLanguage());
            statement.setInt(       6, book.getPages());
            statement.setInt(       7, book.getPublication_year());
            statement.setString(    8, book.getPublisher().getName());
            statement.setString(    9, book.getGenre().getName());


            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgBookDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao inserir livro: livro já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir livro: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir livro.");
            }
        }
    }

    @Override
    public Book read(int prodCod) throws SQLException {
        Book book = new Book();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, prodCod);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {

                    book.setCod(                                 prodCod);
                    book.setISBN(               result.getString("ISBN"));
                    book.setTitle(              result.getString("title"));
                    book.setEdition(            result.getInt("bedition"));
                    book.setLanguage(           result.getString("blanguage"));
                    book.setPages(              result.getInt("pages"));
                    book.setPublication_year(   result.getInt("publication_year"));

                    PublisherDAO daoPub;
                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        daoPub = daoFactory.getPublisherDAO();
                        book.setPublisher(  daoPub.read(result.getString("fk_publisher_fname")) );

                    } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                        System.out.println(ex.getMessage());
                        book.setPublisher(new Publisher("NOT_FOUND", "NOT_FOUND") );
                    }

                    GenreDAO daoGen;
                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        daoGen = daoFactory.getGenreDAO();
                        book.setGenre(  daoGen.read(result.getString("fk_genre_fname"))  );

                    } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                        System.out.println(ex.getMessage());
                        book.setGenre(new Genre("NOT_FOUND", 0.0) );
                    }
                } else {
                    throw new SQLException("Erro ao visualizar: livro não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgBookDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: livro não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar livro.");
            }
        }

        return book;
    }

    @Override
    public void update(Book book) throws SQLException {
        String query;

        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(    1, book.getISBN());
            statement.setString(    2, book.getTitle());
            statement.setInt(       3, book.getEdition());
            statement.setString(    4, book.getLanguage());
            statement.setInt(       5, book.getPages());
            statement.setInt(       6, book.getPublication_year());
            statement.setString(    7, book.getPublisher().getName());
            statement.setString(    8, book.getGenre().getName());
            statement.setInt(       9, book.getCod());


            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: livro não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgBookDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: livro não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_user_login")) {
                throw new SQLException("Erro ao editar livro: livro já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar livro: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar livro.");
            }
        }
    }

    @Override
    public void delete(int prodCod) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, prodCod);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: livro não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgBookDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: livro não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir livro.");
            }
        }
    }

    @Override
    public List<Book> all() throws SQLException {
        List<Book> bookList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Book book = new Book();
                book.setCod(                result.getInt("fk_product_cod"));
                book.setISBN(               result.getString("ISBN"));
                book.setTitle(              result.getString("title"));
                book.setEdition(            result.getInt("bedition"));
                book.setLanguage(           result.getString("blanguage"));
                book.setPages(              result.getInt("pages"));
                book.setPublication_year(   result.getInt("publication_year"));

                PublisherDAO daoPub;
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    daoPub = daoFactory.getPublisherDAO();
                    book.setPublisher(  daoPub.read(result.getString("fk_publisher_fname")) );

                } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                    System.out.println(ex.getMessage());
                    book.setPublisher(new Publisher("NOT_FOUND", "NOT_FOUND") );
                }

                GenreDAO daoGen;
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    daoGen = daoFactory.getGenreDAO();
                    book.setGenre(  daoGen.read(result.getString("fk_genre_fname"))  );

                } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                    System.out.println(ex.getMessage());
                    book.setGenre(new Genre("NOT_FOUND", 0.0) );
                }

                bookList.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgBookDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar livros.");
        }

        return bookList;
    }

}
