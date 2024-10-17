package by.bsuir.dao.service.impl;

import by.bsuir.dao.AbstractDaoImpl;
import by.bsuir.dao.Tables;
import by.bsuir.dao.service.HotelDao;
import by.bsuir.entity.Address;
import by.bsuir.entity.Hotel;
import by.bsuir.entity.Room;
import by.bsuir.exceptions.DaoException;
import by.bsuir.mapper.RecordMapperFactory;

import java.util.List;

public class HotelDaoImpl extends AbstractDaoImpl<Hotel> implements HotelDao {

    private static final String SAVE = "insert into " + Tables.HOTELS +
            " (id, name, description, adderess_id, level, available_to_book, image_path) " +
            "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_NAME = "select * from " + Tables.HOTELS + " where name=?";
    private static final String FIND_BY_DESCRIPTION = "select * from " + Tables.HOTELS + " where description=?";
    private static final String FIND_BY_ADDRESS = "select * from " + Tables.HOTELS + " where address_id=?";
    private static final String FIND_BY_LEVEL = "select * from " + Tables.HOTELS + " where level=?";
    private static final String FIND_BY_ROOM = "select * from " + Tables.ROOMS + " where hotel_id=?";
    private static final String FIND_BY_AVAILABLE = "select * from " + Tables.HOTELS + " where available_to_book=?";
    private static final String UPDATE =  "update " + Tables.HOTELS +
            " set name=?, description=?, adderess_id=?, level=?, available_to_book=?, image_path=? where id=?";

    public HotelDaoImpl() {
        super(RecordMapperFactory.getInstance().getHotelRecordMapper(), Tables.HOTELS);
    }

    @Override
    public void save(Hotel hotel) throws DaoException {
        executeInsertQuery(SAVE, hotel.getId(), hotel.getName(), hotel.getDescription(),
                hotel.getAddressId(), hotel.getLevel(), hotel.isAvailableToBook(), hotel.getImagePath());
    }

    @Override
    public List<Hotel> findByName(String name) throws DaoException {
        return executeQuery(FIND_BY_NAME, name);
    }

    @Override
    public List<Hotel> findByDescription(String description) throws DaoException {
        return executeQuery(FIND_BY_DESCRIPTION, description);
    }

    @Override
    public Hotel findByAddress(Address address) throws DaoException {
        return executeQuerySingleton(FIND_BY_ADDRESS, address.getId());
    }

    @Override
    public List<Hotel> findByLevel(int level) throws DaoException {
        return executeQuery(FIND_BY_LEVEL, level);
    }

    @Override
    public List<Hotel> findByRoom(Room room) throws DaoException {
        return executeQuery(FIND_BY_ROOM, room.getId());
    }

    @Override
    public List<Hotel> findByAvailable(boolean flag) throws DaoException {
        return executeQuery(FIND_BY_AVAILABLE, flag);
    }

    @Override
    public void update(Hotel hotel) throws DaoException {
        executeInsertQuery(UPDATE, hotel.getName(), hotel.getDescription(), hotel.getAddressId(),
                hotel.getLevel(), hotel.isAvailableToBook(), hotel.getImagePath(), hotel.getId());
    }
}
