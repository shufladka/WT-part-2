package by.bsuir.service;

import by.bsuir.entity.Role;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;

import java.util.List;

public interface RoleService {
    List<Role> findAll() throws ServiceException, DaoException;
}
