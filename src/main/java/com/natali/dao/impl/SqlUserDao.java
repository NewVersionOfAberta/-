package com.natali.dao.impl;

import com.natali.dao.DaoException;

import com.natali.dao.interfaces.UserDao;
import com.natali.entity.User;
import com.natali.dto.UserData;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import java.util.Properties;
@Slf4j
public class SqlUserDao implements UserDao {
    //private final ConnectionProvider connectionProvider;
    final String propFileName = "db.properties";




    @Override
    public User authentification(String login, String password) throws DaoException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            log.error("JConnector not found!");
            throw new DaoException(e);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("The \"db.properties\" file not founded!");
            throw new DaoException(e);
        }

        final String dbHost = properties.getProperty("host");
        final String dbUsername = properties.getProperty("username");
        final String dbPassword = properties.getProperty("password");

        try (Connection connection = DriverManager.getConnection(dbHost, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM user WHERE userLogin=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (!password.equals(resultSet.getString("userPassword"))) {
                    return null;
                }

                User user = new User();
                user.setId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("userName"));
                user.setSurname(resultSet.getString("userSurname"));
                user.setEmail(resultSet.getString("userEmail"));

                log.info("User was created");

                return user;
            }

        } catch (SQLException e) {
            log.error("Connection to DB can't be established!");
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public boolean registration(UserData userData) throws DaoException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new DaoException(e);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }


        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("The \"db.properties\" file not founded!");
            throw new DaoException(e);
        }

        final String dbHost = properties.getProperty("host");
        final String dbUsername = properties.getProperty("username");
        final String dbPassword = properties.getProperty("password");

        try (Connection connection = DriverManager.getConnection(dbHost, dbUsername, dbPassword)) {
            String sql = "INSERT INTO user (userName, userSurname, userLogin, userEmail, userPAssword) Values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userData.getName());
            preparedStatement.setString(2, userData.getSurname());
            preparedStatement.setString(3, userData.getLogin());
            preparedStatement.setString(4, userData.getEmail());
            preparedStatement.setString(5, userData.getPassword());

            preparedStatement.executeUpdate();
            log.info("Table was updated");
            return true;
        } catch (SQLException e) {
            log.error("Connection exception");
            throw new DaoException(e);
        }
    }
}
