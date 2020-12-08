package com.natali.dao;

import com.natali.entity.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    T getById(int id) throws DaoException;
    List<T> getAll() throws DaoException;
    void add(T entity) throws DaoException;
    void delete(T entity) throws DaoException;
    void update(T entity) throws DaoException;
}
