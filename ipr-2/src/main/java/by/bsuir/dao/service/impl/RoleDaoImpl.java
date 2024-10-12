package by.bsuir.dao.service.impl;

import by.bsuir.dao.AbstractDaoImpl;
import by.bsuir.dao.Tables;
import by.bsuir.dao.service.RoleDao;
import by.bsuir.entity.Role;
import by.bsuir.exceptions.DaoException;
import by.bsuir.mapper.RecordMapperSingleton;

import java.util.List;

public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {

    private static final String SAVE = "insert into " + Tables.ROLES + " (id, name, description) values (?, ?, ?)";
    private static final String FIND_ALL = "select * from " + Tables.ROLES;
    private static final String FIND_BY_ID = "select * from " + Tables.ROLES + " where id=?";
    private static final String FIND_BY_NAME = "select * from " + Tables.ROLES + " where name=?";
    private static final String FIND_BY_DESCRIPTION = "select * from " + Tables.ROLES + " where description=?";
    private static final String UPDATE = "update " + Tables.ROLES + " set name=?, description=? where id=?";
    private static final String DELETE = "delete from " + Tables.ROLES + " where id=?";

    public RoleDaoImpl() {
        super(RecordMapperSingleton.getInstance().getRoleRecordMapper(), Tables.ROLES);
    }

    @Override
    public void save(Role role) throws DaoException {
        executeInsertQuery(SAVE, role.getId(), role.getName(), role.getDescription());
    }

    @Override
    public List<Role> findAll() throws DaoException {
        return executeQuery(FIND_ALL);
    }

    @Override
    public Role findById(int id) throws DaoException {
        return executeQuerySingleton(FIND_BY_ID, id);
    }

    @Override
    public Role findByName(String name) throws DaoException {
        return executeQuerySingleton(FIND_BY_NAME, name);
    }

    @Override
    public Role findByDescription(String description) throws DaoException {
        return executeQuerySingleton(FIND_BY_DESCRIPTION, description);
    }

    @Override
    public void update(Role role) throws DaoException {
        executeUpdateQuery(UPDATE, role.getName(), role.getDescription(), role.getId());
    }

    @Override
    public void delete(int id) throws DaoException {
        executeUpdateQuery(DELETE, id);
    }
}
