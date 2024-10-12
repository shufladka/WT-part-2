package by.bsuir.service;

import by.bsuir.entity.Address;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;

import java.util.List;

public interface AddressService {
    List<Address> findAll() throws ServiceException, DaoException;
    Address findById(int id) throws ServiceException, DaoException;
}
