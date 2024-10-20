package by.bsuir.dao;

import by.bsuir.dao.service.*;
import by.bsuir.dao.service.impl.*;

public class DaoFactory {

    private static DaoFactory INSTANCE;
    private final BookDao bookDao = new BookDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    public BookDao getBookDao() {
        return bookDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public static DaoFactory getInstance()
    {
        if (INSTANCE == null) {
            synchronized (DaoFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DaoFactory();
                }
            }
        }

        return INSTANCE;
    }
}
