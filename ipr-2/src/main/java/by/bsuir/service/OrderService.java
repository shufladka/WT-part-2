package by.bsuir.service;

import by.bsuir.entity.Order;
import by.bsuir.entity.Person;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void save(Person person, int roomId) throws DaoException, ServiceException, IOException;
    List<Order> findAll() throws ServiceException, DaoException;
    List<Order> findByPersonId(int personId) throws ServiceException, DaoException;
    List<Order> findByRoomId(int roomId) throws ServiceException, DaoException;
    List<Order> findByCreationDate(LocalDate from, LocalDate to) throws ServiceException, DaoException;
    void update(Order order) throws ServiceException, DaoException, IOException;
    void delete(int orderId) throws ServiceException, DaoException, IOException;
}
