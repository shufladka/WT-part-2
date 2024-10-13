package by.bsuir.dao.service;

import by.bsuir.dao.Dao;
import by.bsuir.entity.Order;
import by.bsuir.exceptions.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends Dao<Order> {
    void save(Order order) throws DaoException;
    List<Order> findByPersonId(int personId) throws DaoException;
    List<Order> findByRoomId(int roomId) throws DaoException;
    List<Order> findByCreationDate(LocalDate from, LocalDate to) throws DaoException;
    void update(Order order) throws DaoException;
    void delete(int orderId) throws DaoException;
}
