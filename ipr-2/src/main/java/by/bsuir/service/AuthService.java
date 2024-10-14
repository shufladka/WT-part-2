package by.bsuir.service;

import by.bsuir.entity.Person;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;

public interface AuthService {
    boolean registration(Person person) throws ServiceException, DaoException;
    Person login(String username, String password) throws ServiceException, DaoException;
    void logout(Person person) throws ServiceException;
    String serializePersonBase64(Person person, Object... params);
    Person deserializePersonBase64(String base64);
    boolean isAdmin(Person person) throws ServiceException, DaoException;
}
