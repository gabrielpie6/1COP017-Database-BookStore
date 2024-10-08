package com.bd12024.BookStore.dao;

import com.bd12024.BookStore.entities.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PgCustomerDAO implements CustomerDAO {
        private final Connection connection;

        private static final String CREATE_QUERY =
            "INSERT INTO bookstore.customer (cpf, fname, lname, email, CEP, address_street, address_number) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?);";

        private static final String ALL_QUERY =
            "SELECT cpf, fname, lname, email, CEP, address_street, address_number " +
            "FROM bookstore.customer " +
            "ORDER BY cpf ASC;";

        private static final String READ_QUERY =
            "SELECT cpf, fname, lname, email, CEP, address_street, address_number " +
            "FROM bookstore.customer " +
            "WHERE cpf = ?;";

        private static final String UPDATE_QUERY =
            "UPDATE bookstore.customer " +
            "SET fname = ?, lname = ?, email = ?, CEP = ?, address_street = ?, address_number = ? " +
            "WHERE cpf = ?;";

        private static final String DELETE_QUERY =
            "DELETE FROM bookstore.customer " +
            "WHERE cpf = ?;";

        public PgCustomerDAO(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void create(Customer customer) throws SQLException {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(    1, customer.getCpf());
            statement.setString(    2, customer.getFname());
            statement.setString(    3, customer.getLname());
            statement.setString(    4, customer.getEmail());
            statement.setString(    5, customer.getCEP());
            statement.setString(    6, customer.getAddress_street());
            statement.setInt(       7, customer.getAddress_number());

            statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PgCustomerDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
                throw new SQLException("Erro ao inserir cliente.");
            }
        }

        @Override
        public Customer read(String cpf) throws SQLException {
            Customer customer = null;

            try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
                statement.setString(1, cpf);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        customer = new Customer();
                        customer.setCpf(result.getString("cpf"));
                        customer.setFname(result.getString("fname"));
                        customer.setLname(result.getString("lname"));
                        customer.setEmail(result.getString("email"));
                        customer.setCEP(result.getString("CEP"));
                        customer.setAddress_street(result.getString("address_street"));
                        customer.setAddress_number(result.getInt("address_number"));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PgCustomerDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
                throw new SQLException("Erro ao buscar cliente.");
            }

            return customer;
        }


        @Override
        public void update(Customer customer) throws SQLException {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, customer.getFname());
            statement.setString(2, customer.getLname());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getCEP());
            statement.setString(5, customer.getAddress_street());
            statement.setInt(6, customer.getAddress_number());
            statement.setString(7, customer.getCpf());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: cliente não encontrado.");
            }
            } catch (SQLException ex) {
                Logger.getLogger(PgCustomerDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
                throw new SQLException("Erro ao editar cliente.");
            }
        }

        @Override
        public List<Customer> all() throws SQLException {
            List<Customer> customerList = new ArrayList<>();

            try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Customer customer = new Customer();
                customer.setCpf(result.getString("cpf"));
                customer.setFname(result.getString("fname"));
                customer.setLname(result.getString("lname"));
                customer.setEmail(result.getString("email"));
                customer.setCEP(result.getString("CEP"));
                customer.setAddress_street(result.getString("address_street"));
                customer.setAddress_number(result.getInt("address_number"));
                customerList.add(customer);
            }
            } catch (SQLException ex) {
                Logger.getLogger(PgCustomerDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
                throw new SQLException("Erro ao listar clientes.");
            }

            return customerList;
        }

        @Override
        public void delete(String cpf) throws SQLException {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, cpf);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: cliente não encontrado.");
            }
            } catch (SQLException ex) {
                Logger.getLogger(PgCustomerDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
                throw new SQLException("Erro ao excluir cliente.");
            }
        }
}
