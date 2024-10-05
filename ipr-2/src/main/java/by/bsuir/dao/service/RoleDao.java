package by.bsuir.dao.service;

import by.bsuir.entity.Role;
import by.bsuir.exceptions.DaoException;

import java.util.List;

public interface RoleDao {
    void save(Role role) throws DaoException;
    List<Role> findAll() throws DaoException;
    Role findById(int id) throws DaoException;
    Role findByName(String name) throws DaoException;
    Role findByDescription(String description) throws DaoException;
    void update(Role role) throws DaoException;
    void delete(int id) throws DaoException;
}
