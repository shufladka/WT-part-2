package by.bsuir.service;

import by.bsuir.entity.Address;
import by.bsuir.mapper.RecordMapper;
import by.bsuir.service.impl.AuthServiceImpl;

public class ServiceSingleton {

    private static ServiceSingleton INSTANCE;
    private final AuthService authService = new AuthServiceImpl();

    public AuthService getAuthService() {
        return authService;
    }

    public static ServiceSingleton getInstance()
    {
        if (INSTANCE == null) {
            synchronized (ServiceSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceSingleton();
                }
            }
        }

        return INSTANCE;
    }
}
