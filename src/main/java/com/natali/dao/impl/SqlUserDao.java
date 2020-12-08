package com.natali.dao.impl;

import com.natali.dao.Dao;
import com.natali.dao.DaoException;
import com.natali.dao.connection.ConnectionProvider;
import com.natali.dao.connection.ConnectionProviderException;
import com.natali.entity.Role;
import com.natali.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDao implements Dao<User> {
    private final ConnectionProvider connectionProvider;

    public SqlUserDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() throws DaoException {
        Connection connection = null;
        try {
            connection = connectionProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.user");
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getString("role"));
                users.add(new User(id, name, password, role));
            }
            return users;
        } catch (SQLException | ConnectionProviderException e) {
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
    }

    @Override
    public void add(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void update(User entity) {

    }
}
