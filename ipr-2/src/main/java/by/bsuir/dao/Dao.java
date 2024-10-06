package by.bsuir.dao;

import by.bsuir.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> findAll() throws DaoException;
    T getMaxById() throws DaoException;
    T findById(int id) throws DaoException;
    void delete(int id) throws DaoException;
}
