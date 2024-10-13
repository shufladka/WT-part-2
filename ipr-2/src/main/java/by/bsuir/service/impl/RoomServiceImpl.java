package by.bsuir.service.impl;

import by.bsuir.dao.DaoSingleton;
import by.bsuir.dao.service.RoomDao;
import by.bsuir.entity.Room;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    DaoSingleton dao = DaoSingleton.getInstance();
    RoomDao roomDao = dao.getRoomDao();

    @Override
    public List<Room> findAll() throws ServiceException, DaoException {
        return roomDao.findAll();
    }

    @Override
    public List<Room> findByHotelId(int hotelId) throws DaoException {
        return roomDao.findByHotelId(hotelId);
    }
}
