package by.bsuir.dao;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.connection.ProxyConnection;
import by.bsuir.exceptions.DaoException;
import by.bsuir.mapper.RecordMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomQueryExecutor<T> {
    private final RecordMapper<T> mapper;

    public CustomQueryExecutor(RecordMapper<T> mapper) {
        this.mapper = mapper;
    }

    /**
     * Метод создает запрос в БД
     * */
    private PreparedStatement createStatement(String query, Object... params) throws DaoException {
        try {
            ConnectionPool instanceConnection = ConnectionPool.getInstance();
            ProxyConnection proxyConnection = instanceConnection.getConnection();
            PreparedStatement preparedStatement = proxyConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index, param);
                index++;
            }

            instanceConnection.releaseConnection(proxyConnection);
            return preparedStatement;

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * Метод обновляет поля в таблице
     * */
    protected void executeUpdateQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * Метод для добавления записей в таблице
     * */
    protected int executeInsertQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                return generatedKey.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return 0;
    }

    /**
     * Метод для выполнения запроса в БД
     * */
    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            return getEntitiesList(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * Метод возвращает список сущностей
     */
    private List<T> getEntitiesList(ResultSet resultSet) throws DaoException {
        List<T> entities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                entities.add(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return entities;
    }

    /**
     * Метод для получения единичного результата запроса в БД
     * */
    protected T executeQuerySingleton(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() != 1) {
            return null;
        }
        return items.get(0);
    }
}
