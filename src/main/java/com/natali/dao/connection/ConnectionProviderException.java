package com.natali.dao.connection;

public class ConnectionProviderException extends Exception{
    public ConnectionProviderException(Exception associatedException) {
        super(associatedException);
    }
}
