package com.natali.dao.connection;

import java.sql.Connection;

public interface ConnectionProvider {
    Connection getConnection() throws ConnectionProviderException;
}
