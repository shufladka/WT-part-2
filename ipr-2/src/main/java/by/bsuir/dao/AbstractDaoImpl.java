package by.bsuir.dao;

import by.bsuir.exceptions.DaoException;
import by.bsuir.mapper.RecordMapper;

import java.util.List;

public abstract class AbstractDaoImpl<T> extends CustomQueryExecutor<T> implements Dao<T> {

    private final String table;

    public AbstractDaoImpl(RecordMapper<T> recordMapper, String table) {
        super(recordMapper);
        this.table = table;
    }

    @Override
    public List<T> findAll() throws DaoException {
        String query = "select * from " + table;
        return executeQuery(query);
    }

    @Override
    public T getMaxById() throws DaoException {
        String query = "select * from " + table + " where id = (select max(id) from " + table + ")";
        return executeQuerySingleton(query);
    }

    @Override
    public T findById(int id) throws DaoException {
        String query = "select * from " + table + " where id=?";
        return executeQuerySingleton(query, id);
    }

    @Override
    public void delete(int id) throws DaoException {
        String deleteQuery = "delete from " + table + " where id=?";
        executeUpdateQuery(deleteQuery, id);
    }
}
