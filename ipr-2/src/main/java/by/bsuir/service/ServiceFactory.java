package by.bsuir.service;

import by.bsuir.service.impl.*;

public class ServiceFactory {

    private static ServiceFactory INSTANCE;
    private final AuthService authService = new AuthServiceImpl();
    private final HotelService hotelService = new HotelServiceImpl();
    private final AddressService addressService = new AddressServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();
    private final RoleService roleService = new RoleServiceImpl();
    private final PersonService personService = new PersonServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
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

    public OrderService getOrderService() {
        return orderService;
    }

    public MailService getMailService() {
        return mailService;
    }

    public static ServiceFactory getInstance()
    {
        if (INSTANCE == null) {
            synchronized (ServiceFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceFactory();
                }
            }
        }

        return INSTANCE;
    }
}
