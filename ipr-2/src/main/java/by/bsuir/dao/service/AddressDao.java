package by.bsuir.dao.service;

import by.bsuir.dao.Dao;
import by.bsuir.entity.Address;
import by.bsuir.exceptions.DaoException;

import java.util.List;

public interface AddressDao extends Dao<Address> {
    void save(Address address) throws DaoException;
    List<Address> findAll() throws DaoException;
    Address findById(int id) throws DaoException;
    List<Address> findByRegion(String region) throws DaoException;
    List<Address> findByCity(String city) throws DaoException;
    List<Address> findByStreet(String street) throws DaoException;
    List<Address> findByBuilding(String building) throws DaoException;
    List<Address> findByZip(String zip) throws DaoException;
    void update(Address address) throws DaoException;
    void delete(int id) throws DaoException;
}
