package by.bsuir.service.impl;

import by.bsuir.dao.DaoFactory;
import by.bsuir.dao.service.PersonDao;
import by.bsuir.entity.Person;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    DaoFactory dao = DaoFactory.getInstance();
    PersonDao personDao = dao.getPersonDao();

    @Override
    public List<Person> findAll() throws ServiceException, DaoException {
        return personDao.findAll();
    }

    @Override
    public Person findById(int id) throws ServiceException, DaoException {
        return personDao.findById(id);
    }
}
