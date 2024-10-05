package by.bsuir.mapper;

import by.bsuir.exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RecordMapper<T> {
    T map(ResultSet resultSet) throws SQLException, DaoException;
}
