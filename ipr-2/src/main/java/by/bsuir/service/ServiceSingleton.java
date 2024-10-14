package by.bsuir.service;

import by.bsuir.service.impl.*;

public class ServiceSingleton {

    private static ServiceSingleton INSTANCE;
    private final AuthService authService = new AuthServiceImpl();
    private final HotelService hotelService = new HotelServiceImpl();
    private final AddressService addressService = new AddressServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();
    private final RoleService roleService = new RoleServiceImpl();
    private final PersonService personService = new PersonServiceImpl();
    private final MailService mailService = new MailServiceImpl();

    public AuthService getAuthService() {
        return authService;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public MailService getMailService() {
        return mailService;
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
