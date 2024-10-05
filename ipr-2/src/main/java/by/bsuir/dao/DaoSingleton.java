package by.bsuir.dao;

import by.bsuir.dao.service.AddressDao;

public class DaoSingleton {

    private static DaoSingleton INSTANCE;


    public static DaoSingleton getInstance()
    {
        if (INSTANCE == null) {
            synchronized (DaoSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DaoSingleton();
                }
            }
        }

        return INSTANCE;
    }
}
