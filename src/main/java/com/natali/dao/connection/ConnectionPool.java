package com.natali.dao.connection;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

public class ConnectionPool implements ConnectionProvider{
    private static final String URL_KEY_NAME = "url";
    private static final String USER_KEY_NAME = "user";
    private static final String PASSWORD_KEY_NAME = "password";
    private static final String CONNECTION_COUNT_KEY_NAME = "connection_count";

    private final Object lock = new Object();
    private final Queue<Connection> connections;
    private final int connectionCount;

    public ConnectionPool(Properties properties) throws ConnectionProviderException {
        String url = properties.getProperty(URL_KEY_NAME);
        String user = properties.getProperty(USER_KEY_NAME);
        String password = properties.getProperty(PASSWORD_KEY_NAME);
        connectionCount = Integer.parseInt(properties.getProperty(CONNECTION_COUNT_KEY_NAME));


        connections = new LinkedList<>();
        for (int i = 0; i < connectionCount; i++) {
            try {
                connections.add(DriverManager.getConnection(url, user, password));
            } catch (SQLException e) {
                throw new ConnectionProviderException(e);
            }
        }
    }


    @Override
    public Connection getConnection() throws ConnectionProviderException {
        Connection connection;
        synchronized (lock) {
            while (connections.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new ConnectionProviderException(e);
                }
            }
            connection = connections.poll();
        }
        return getConnectionProxy(connection);
    }

    private Connection getConnectionProxy(Connection connection) {
        return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[] { Connection.class },
                ((proxy, method, args) -> {
                    if (method.getName().equals("close")) {
                        synchronized (lock) {
                            connections.add(connection);
                            lock.notify();
                        }
                        return null;
                    }
                    return method.invoke(connection, args);
                }));
    }

    public void closeAllConnections() throws ConnectionProviderException {
        if (connections.size() != connectionCount) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
        }
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionProviderException(e);
            }
        }
    }
}
