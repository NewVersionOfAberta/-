package com.natali.dao;

import com.natali.dao.connection.ConnectionPool;
import com.natali.dao.connection.ConnectionProviderException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
class DaoContext {
    private static ConnectionPool connectionPool;

    static void init(InputStream inputStream) throws IOException, ConnectionProviderException {
        Properties properties = new Properties();
        properties.load(inputStream);

        connectionPool = new ConnectionPool(properties);
        DaoFactory.init(connectionPool);
    }

    static void stop() throws ConnectionProviderException {
        connectionPool.closeAllConnections();
    }
}
