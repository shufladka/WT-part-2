package by.bsuir.dao.mapper.impl;

import by.bsuir.dao.mapper.Attributes;
import by.bsuir.dao.mapper.RecordMapper;
import by.bsuir.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRecordMapperImpl implements RecordMapper<Role> {

    @Override
    public Role map(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt(Attributes.ID));
        role.setName(resultSet.getString(Attributes.NAME));
        role.setDescription(resultSet.getString(Attributes.DESCRIPTION));
        return role;
    }
}
