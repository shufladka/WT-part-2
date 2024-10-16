package by.bsuir.service.impl;

import by.bsuir.dao.DaoFactory;
import by.bsuir.dao.service.AddressDao;
import by.bsuir.entity.Address;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    DaoFactory dao = DaoFactory.getInstance();
    AddressDao addressDao = dao.getAddressDao();

    @Override
    public List<Address> findAll() throws ServiceException, DaoException {
        return addressDao.findAll();
    }

    @Override
    public Address findById(int id) throws ServiceException, DaoException {
        return addressDao.findById(id);
    }
}
