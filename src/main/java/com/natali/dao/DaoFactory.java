package com.natali.dao;

import com.natali.dao.connection.ConnectionProvider;
import com.natali.dao.impl.SqlRentDao;
import com.natali.dao.impl.SqlUserDao;
import com.natali.dao.interfaces.RentDao;
import com.natali.dao.interfaces.UserDao;
import com.natali.entity.User;
import lombok.Getter;

@Getter
public class DaoFactory {
    @Getter
    private static DaoFactory instance;

    private static ConnectionProvider connectionProvider;

    private final UserDao userDao;
    private final RentDao rentDao;

    public static void init(ConnectionProvider connectionProvider) {
        DaoFactory.connectionProvider = connectionProvider;
        instance = new DaoFactory();
    }

    private DaoFactory() {
        userDao = new SqlUserDao();
        rentDao = new SqlRentDao(connectionProvider);
    }
}
