package by.bsuir.dao;

import by.bsuir.dao.service.*;
import by.bsuir.dao.service.impl.*;

public class DaoFactory {

    private static DaoFactory INSTANCE;
    private final AddressDao addressDao = new AddressDaoImpl();
    private final HotelDao hotelDao = new HotelDaoImpl();
    private final PersonDao personDao = new PersonDaoImpl();
    private final RoleDao roleDao = new RoleDaoImpl();
    private final RoomDao roomDao = new RoomDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();

    public AddressDao getAddressDao() {
        return addressDao;
    }

    public HotelDao getHotelDao() {
        return hotelDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
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
