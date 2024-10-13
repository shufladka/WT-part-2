package by.bsuir.service;

import by.bsuir.entity.Hotel;
import by.bsuir.entity.Room;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;

import java.util.List;

public interface RoomService {
    List<Room> findAll() throws ServiceException, DaoException;
    List<Room> findByHotelId(int hotelId) throws ServiceException, DaoException;
}
