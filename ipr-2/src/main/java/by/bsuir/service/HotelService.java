package by.bsuir.service;

import by.bsuir.entity.Hotel;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;

import java.util.List;

public interface HotelService {
    List<Hotel> findAll() throws ServiceException, DaoException;
}
