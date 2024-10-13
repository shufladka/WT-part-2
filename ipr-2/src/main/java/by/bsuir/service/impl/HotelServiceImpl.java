package by.bsuir.service.impl;

import by.bsuir.dao.DaoSingleton;
import by.bsuir.dao.service.HotelDao;
import by.bsuir.entity.Hotel;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.HotelService;

import java.util.List;

public class HotelServiceImpl implements HotelService {

    DaoSingleton dao = DaoSingleton.getInstance();
    HotelDao hotelDao = dao.getHotelDao();

    @Override
    public List<Hotel> findAll() throws ServiceException, DaoException {
        return hotelDao.findAll();
    }
}