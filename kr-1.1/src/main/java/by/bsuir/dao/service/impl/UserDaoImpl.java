package by.bsuir.dao.service.impl;

import by.bsuir.dao.AbstractDaoImpl;
import by.bsuir.dao.Links;
import by.bsuir.dao.service.UserDao;
import by.bsuir.domain.User;

public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(Links.PEOPLE);
    }
}
