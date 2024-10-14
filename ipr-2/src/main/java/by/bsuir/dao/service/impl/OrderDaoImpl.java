package by.bsuir.dao.service.impl;

import by.bsuir.dao.AbstractDaoImpl;
import by.bsuir.dao.Tables;
import by.bsuir.dao.service.OrderDao;
import by.bsuir.entity.Order;
import by.bsuir.exceptions.DaoException;
import by.bsuir.mapper.RecordMapperSingleton;

import java.time.LocalDate;
import java.util.List;

public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDao {

    private static final String SAVE = "insert into " + Tables.ORDERS +
            " (id, person_id, room_id, created_at, status) " +
            "values (?, ?, ?, ?, ?)";
    private static final String FIND_BY_PERSON_ID = "select * from " + Tables.ORDERS + " where person_id=?";
    private static final String FIND_BY_ROOM_ID = "select * from " + Tables.ORDERS + " where room_id=?";
    private static final String FIND_BY_CREATION_DATE = "select * from " + Tables.ROOMS + " where created_at>=? " +
            "and created_at<=?";
    private static final String UPDATE = "update " + Tables.ORDERS +
            " set person_id=?, room_id=?, updated_at=?, status=? where id=?";
    private static final String DELETE = "update " + Tables.ORDERS +
            " set closed_at=?, status=? where id=?";

    public OrderDaoImpl() {
        super(RecordMapperSingleton.getInstance().getOrderRecordMapper(), Tables.ORDERS);
    }

    @Override
    public void save(int id, int personId, int roomId) throws DaoException {
        executeInsertQuery(SAVE, id, personId, roomId,
                LocalDate.now(), "CREATED");
    }

    @Override
    public List<Order> findByPersonId(int personId) throws DaoException {
        return executeQuery(FIND_BY_PERSON_ID, personId);
    }

    @Override
    public List<Order> findByRoomId(int roomId) throws DaoException {
        return executeQuery(FIND_BY_ROOM_ID, roomId);
    }

    @Override
    public List<Order> findByCreationDate(LocalDate from, LocalDate to) throws DaoException {
        return executeQuery(FIND_BY_CREATION_DATE, from, to);
    }

    @Override
    public void update(Order order) throws DaoException {
        executeInsertQuery(UPDATE, order.getPersonId(), order.getRoomId(),
                LocalDate.now(), "IN_PROGRESS", order.getId());
    }

    @Override
    public void delete(int orderId) throws DaoException {
        executeInsertQuery(DELETE, LocalDate.now(), "CLOSED", orderId);
    }
}
