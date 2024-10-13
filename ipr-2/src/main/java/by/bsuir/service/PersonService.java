package by.bsuir.service;

import by.bsuir.entity.Person;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;

import java.util.List;

public interface PersonService {
    List<Person> findAll() throws ServiceException, DaoException;
}
