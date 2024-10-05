package by.bsuir.dao.service;

import by.bsuir.entity.Person;
import by.bsuir.exceptions.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface PersonDao {
    void save(Person person) throws DaoException;
    List<Person> findAll() throws DaoException;
    Person findById(int id) throws DaoException;
    Person findByUsername(String username) throws DaoException;
    List<Person> findByName(String firstName, String lastName) throws DaoException;
    List<Person> findByBirthday(LocalDate birthday) throws DaoException;
    Person findByEmail(String email) throws DaoException;
    List<Person> findByRole(String role) throws DaoException;
    void update(Person person) throws DaoException;
    void delete(int id) throws DaoException;
}
