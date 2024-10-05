package by.bsuir.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RecordMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
