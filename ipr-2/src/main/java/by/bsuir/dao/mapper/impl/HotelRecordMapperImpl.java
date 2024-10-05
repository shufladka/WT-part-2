package by.bsuir.dao.mapper.impl;

import by.bsuir.dao.mapper.Attributes;
import by.bsuir.dao.mapper.RecordMapper;
import by.bsuir.entity.Address;
import by.bsuir.entity.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelRecordMapperImpl implements RecordMapper<Hotel> {

    @Override
    public Hotel map(ResultSet resultSet) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(resultSet.getInt(Attributes.ID));
        hotel.setName(resultSet.getString(Attributes.NAME));
        hotel.setDescription(resultSet.getString(Attributes.DESCRIPTION));
        hotel.setAddress(resultSet.getObject(Attributes.ADDRESS_ID, Address.class));
        hotel.setLevel(resultSet.getInt(Attributes.LEVEL));
        // список комнат в отеле берется из таблицы комнат, эту строку пропускаем
        hotel.setAvailableToBook(resultSet.getBoolean(Attributes.AVAILABLE_TO_BOOK));
        hotel.setImagePath(resultSet.getString(Attributes.IMAGE_PATH));
        return hotel;
    }
}
