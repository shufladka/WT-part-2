package by.bsuir.dao.service.impl;

import by.bsuir.dao.service.RoleDao;
import by.bsuir.entity.Role;
import by.bsuir.exceptions.DaoException;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    @Override
    public void save(Role role) throws DaoException {

    }

    @Override
    public List<Role> findAll() throws DaoException {
        return List.of();
    }

    @Override
    public Role findById(int id) throws DaoException {
        return null;
    }

    @Override
    public Role findByName(String name) throws DaoException {
        return null;
    }

    @Override
    public Role findByDescription(String description) throws DaoException {
        return null;
    }

    @Override
    public void update(Role role) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }
}
