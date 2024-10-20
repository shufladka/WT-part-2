package by.bsuir.dao;

import by.bsuir.exceptions.DaoException;

import java.util.List;

public interface Dao<T> {
    void save(T t) throws DaoException;
    List<T> findAll() throws DaoException;
}
