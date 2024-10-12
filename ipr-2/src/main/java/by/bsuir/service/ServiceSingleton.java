package by.bsuir.service;

import by.bsuir.service.impl.AddressServiceImpl;
import by.bsuir.service.impl.AuthServiceImpl;
import by.bsuir.service.impl.HotelServiceImpl;

public class ServiceSingleton {

    private static ServiceSingleton INSTANCE;
    private final AuthService authService = new AuthServiceImpl();
    private final HotelService hotelService = new HotelServiceImpl();
    private final AddressService addressService = new AddressServiceImpl();

    public AuthService getAuthService() {
        return authService;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public AddressService getAddressService() {
        return addressService;
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
