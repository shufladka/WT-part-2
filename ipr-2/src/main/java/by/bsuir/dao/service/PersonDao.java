package by.bsuir.dao.service;

import by.bsuir.dao.Dao;
import by.bsuir.entity.Person;
import by.bsuir.entity.Role;
import by.bsuir.exceptions.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface PersonDao extends Dao<Person> {
    void save(Person person, Role role) throws DaoException;
    List<Person> findAll() throws DaoException;
    Person findById(int id) throws DaoException;
    Person findByUsername(String username) throws DaoException;
    List<Person> findByName(String firstName, String lastName) throws DaoException;
    List<Person> findByBirthDate(LocalDate birthDate) throws DaoException;
    Person findByEmail(String email) throws DaoException;
    List<Person> findByRole(Role role) throws DaoException;
    void update(Person person, Role role) throws DaoException;
    void delete(int id) throws DaoException;
}
