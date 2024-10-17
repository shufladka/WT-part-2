package by.bsuir.service.impl;

import by.bsuir.dao.DaoFactory;
import by.bsuir.dao.service.RoleDao;
import by.bsuir.entity.Role;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    DaoFactory dao = DaoFactory.getInstance();
    RoleDao roleDao = dao.getRoleDao();

    @Override
    public List<Role> findAll() throws ServiceException, DaoException {
        return roleDao.findAll();
    }

    @Override
    public int findAdminRoleId() throws DaoException {
        return roleDao.findByName("ADMIN").getId();
    }
}
