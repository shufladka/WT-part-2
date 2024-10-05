package by.bsuir.dao;

import by.bsuir.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    int save(T entity) throws DaoException;
    List<T> findAll() throws DaoException;
    Optional<T> findById(int id) throws DaoException;
    void update(T entity) throws DaoException;
    void deleteById(int id) throws DaoException;
}
