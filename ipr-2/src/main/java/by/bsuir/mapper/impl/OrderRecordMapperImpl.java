package by.bsuir.mapper.impl;

import by.bsuir.entity.Order;
import by.bsuir.mapper.Attributes;
import by.bsuir.mapper.RecordMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderRecordMapperImpl implements RecordMapper<Order> {

    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt(Attributes.ID));
        order.setPersonId(resultSet.getInt(Attributes.PERSON_ID));
        order.setRoomId(resultSet.getInt(Attributes.ROOM_ID));
        order.setCreatedAt(resultSet.getObject(Attributes.CREATED_AT, LocalDate.class));
        order.setUpdatedAt(resultSet.getObject(Attributes.UPDATED_AT, LocalDate.class));
        order.setClosedAt(resultSet.getObject(Attributes.CLOSED_AT, LocalDate.class));
        order.setStatus(resultSet.getString(Attributes.STATUS));
        return null;
    }
}
