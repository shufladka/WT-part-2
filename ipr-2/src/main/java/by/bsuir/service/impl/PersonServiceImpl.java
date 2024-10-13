package by.bsuir.service.impl;

import by.bsuir.dao.DaoSingleton;
import by.bsuir.dao.service.PersonDao;
import by.bsuir.entity.Person;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    DaoSingleton dao = DaoSingleton.getInstance();
    PersonDao personDao = dao.getPersonDao();

    @Override
    public List<Person> findAll() throws ServiceException, DaoException {
        return personDao.findAll();
    }
}
