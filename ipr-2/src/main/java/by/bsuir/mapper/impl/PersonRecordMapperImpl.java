package by.bsuir.mapper.impl;

import by.bsuir.exceptions.DaoException;
import by.bsuir.mapper.Attributes;
import by.bsuir.mapper.RecordMapper;
import by.bsuir.entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PersonRecordMapperImpl implements RecordMapper<Person> {

    @Override
    public Person map(ResultSet resultSet) throws SQLException, DaoException {
        Person person = new Person();
        person.setId(resultSet.getInt(Attributes.ID));
        person.setUsername(resultSet.getString(Attributes.USERNAME));
        person.setPassword(resultSet.getString(Attributes.PASSWORD));
        person.setFirstName(resultSet.getString(Attributes.FIRST_NAME));
        person.setLastName(resultSet.getString(Attributes.LAST_NAME));
        person.setBirthDate(resultSet.getObject(Attributes.BIRTH_DATE, LocalDate.class));
        person.setEmail(resultSet.getString(Attributes.EMAIL));
        person.setRoleId(resultSet.getInt(Attributes.ROLE_ID));
        return person;
    }
}
