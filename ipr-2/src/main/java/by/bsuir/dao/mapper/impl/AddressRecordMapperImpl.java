package by.bsuir.dao.mapper.impl;

import by.bsuir.dao.mapper.Attributes;
import by.bsuir.dao.mapper.RecordMapper;
import by.bsuir.entity.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRecordMapperImpl implements RecordMapper<Address> {

    @Override
    public Address map(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setId(resultSet.getInt(Attributes.ID));
        address.setRegion(resultSet.getString(Attributes.REGION));
        address.setCity(resultSet.getString(Attributes.CITY));
        address.setStreet(resultSet.getString(Attributes.STREET));
        address.setBuilding(resultSet.getString(Attributes.BUILDING));
        address.setZip(resultSet.getString(Attributes.ZIP));
        return address;
    }
}
