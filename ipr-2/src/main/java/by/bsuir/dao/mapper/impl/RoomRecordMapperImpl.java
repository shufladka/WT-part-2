package by.bsuir.dao.mapper.impl;

import by.bsuir.dao.mapper.Attributes;
import by.bsuir.dao.mapper.RecordMapper;
import by.bsuir.entity.Hotel;
import by.bsuir.entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRecordMapperImpl implements RecordMapper<Room> {

    @Override
    public Room map(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getInt(Attributes.ID));
        room.setName(resultSet.getString(Attributes.NAME));
        room.setCapacity(resultSet.getInt(Attributes.CAPACITY));
        room.setFloor(resultSet.getInt(Attributes.FLOOR));
        room.setBasicPrice(resultSet.getDouble(Attributes.BASIC_PRICE));
        room.setWeekendPrice(resultSet.getDouble(Attributes.WEEKEND_PRICE));
        room.setHotel(resultSet.getObject(Attributes.HOTEL_ID, Hotel.class));
        return room;
    }
}
