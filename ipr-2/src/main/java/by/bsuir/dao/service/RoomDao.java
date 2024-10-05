package by.bsuir.dao.service;

import by.bsuir.dao.Dao;
import by.bsuir.entity.Hotel;
import by.bsuir.entity.Room;
import by.bsuir.exceptions.DaoException;

import java.util.List;

public interface RoomDao extends Dao<Room> {
    void save(Room room) throws DaoException;
    List<Room> findAll() throws DaoException;
    Room findById(int id) throws DaoException;
    List<Room> findByName(String name) throws DaoException;
    List<Room> findByCapacity(int capacity) throws DaoException;
    List<Room> findByFloor(int floor) throws DaoException;
    List<Room> findByPrice(Double min, Double max) throws DaoException;
    List<Room> findByBasicPrice(Double min, Double max) throws DaoException;
    List<Room> findByWeekendPrice(Double min, Double max) throws DaoException;
    List<Room> findByHotel(Hotel hotel) throws DaoException;
    void update(Room room) throws DaoException;
    void delete(int id) throws DaoException;
}
