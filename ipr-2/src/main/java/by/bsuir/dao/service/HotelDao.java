package by.bsuir.dao.service;

import by.bsuir.dao.Dao;
import by.bsuir.entity.Address;
import by.bsuir.entity.Hotel;
import by.bsuir.entity.Room;
import by.bsuir.exceptions.DaoException;

import java.util.List;

public interface HotelDao extends Dao<Hotel> {
    void save(Hotel hotel) throws DaoException;
    List<Hotel> findAll() throws DaoException;
    Hotel findById(int id) throws DaoException;
    List<Hotel> findByName(String name) throws DaoException;
    List<Hotel> findByDescription(String description) throws DaoException;
    Hotel findByAddress(Address address) throws DaoException;
    List<Hotel> findByLevel(int level) throws DaoException;
    List<Hotel> findByRoom(Room room) throws DaoException;
    List<Hotel> findByAvailable(boolean flag) throws DaoException;
    void update(Hotel hotel) throws DaoException;
    void delete(int id) throws DaoException;
}
