package by.bsuir.dao.service.impl;

import by.bsuir.dao.AbstractDaoImpl;
import by.bsuir.dao.Tables;
import by.bsuir.dao.service.RoomDao;
import by.bsuir.entity.Hotel;
import by.bsuir.entity.Room;
import by.bsuir.exceptions.DaoException;
import by.bsuir.mapper.RecordMapperSingleton;

import java.util.List;

public class RoomDaoImpl extends AbstractDaoImpl<Room> implements RoomDao {

    private static final String SAVE = "insert into " + Tables.ROOMS +
            " (id, name, capacity, floor, basic_price, weekend_price, image_path, hotel_id, is_available) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_NAME = "select * from " + Tables.ROOMS + " where name=?";
    private static final String FIND_BY_CAPACITY = "select * from " + Tables.ROOMS + " where capacity=?";
    private static final String FIND_BY_FLOOR = "select * from " + Tables.ROOMS + " where floor=?";
    private static final String FIND_BY_PRICE = "select * from " + Tables.ROOMS + " where basic_price>=? " +
            "and basic_price<=? or weekend_price>=? and weekend_price<=?";
    private static final String FIND_BY_BASIC_PRICE = "select * from " + Tables.ROOMS + " where basic_price>=? " +
            "and basic_price<=?";
    private static final String FIND_BY_WEEKEND_PRICE = "select * from " + Tables.ROOMS + " where weekend_price>=? " +
            "and weekend_price<=?";
    private static final String FIND_BY_ROOM = "select * from " + Tables.ROOMS + " where hotel_id=?";
    private static final String FIND_BY_AVAILABLE = "select * from " + Tables.ROOMS + " where is_available=?";
    private static final String UPDATE =  "update " + Tables.ROOMS +
            " set name=?, capacity=?, floor=?, basic_price=?, weekend_price=?, image_path=?, hotel_id=?, is_available=? where id=?";

    public RoomDaoImpl() {
        super(RecordMapperSingleton.getInstance().getRoomRecordMapper(), Tables.ROOMS);
    }

    @Override
    public void save(Room room) throws DaoException {
        executeInsertQuery(SAVE, room.getId(), room.getName(), room.getCapacity(),
                room.getFloor(), room.getBasicPrice(), room.getWeekendPrice(), room.getImagePath(), room.getHotelId(), true);
    }

    @Override
    public List<Room> findByName(String name) throws DaoException {
        return executeQuery(FIND_BY_NAME, name);
    }

    @Override
    public List<Room> findByCapacity(int capacity) throws DaoException {
        return executeQuery(FIND_BY_CAPACITY, capacity);
    }

    @Override
    public List<Room> findByFloor(int floor) throws DaoException {
        return executeQuery(FIND_BY_FLOOR, floor);
    }

    @Override
    public List<Room> findByPrice(Double min, Double max) throws DaoException {
        return executeQuery(FIND_BY_PRICE, min, max, min, max);
    }

    @Override
    public List<Room> findByBasicPrice(Double min, Double max) throws DaoException {
        return executeQuery(FIND_BY_BASIC_PRICE, min, max);
    }

    @Override
    public List<Room> findByWeekendPrice(Double min, Double max) throws DaoException {
        return executeQuery(FIND_BY_WEEKEND_PRICE, min, max);
    }

    @Override
    public List<Room> findByHotelId(int hotelId) throws DaoException {
        return executeQuery(FIND_BY_ROOM, hotelId);
    }

    @Override
    public List<Room> findByAvailable(boolean available) throws DaoException {
        return executeQuery(FIND_BY_AVAILABLE, available);
    }

    @Override
    public void update(Room room) throws DaoException {
        executeInsertQuery(UPDATE, room.getName(), room.getCapacity(), room.getFloor(),
                room.getBasicPrice(), room.getWeekendPrice(), room.getImagePath(), room.getHotelId(), room.isAvailable(), room.getId());
    }
}
