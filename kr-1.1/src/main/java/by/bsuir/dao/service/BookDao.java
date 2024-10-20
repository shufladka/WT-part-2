package by.bsuir.dao.service;

import by.bsuir.dao.Dao;
import by.bsuir.domain.Book;
import by.bsuir.domain.Role;
import by.bsuir.domain.SecurityCode;

public interface BookDao extends Dao<Book> {
    void update(Book book);
    SecurityCode remove(Integer id, Role role);
}
