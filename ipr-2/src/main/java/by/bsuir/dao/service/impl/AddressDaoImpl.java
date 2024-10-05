package by.bsuir.dao.service.impl;

import by.bsuir.dao.AbstractDaoImpl;
import by.bsuir.dao.Tables;
import by.bsuir.dao.service.AddressDao;
import by.bsuir.entity.Address;
import by.bsuir.exceptions.DaoException;
import by.bsuir.mapper.RecordMapperSingleton;

import java.util.List;

public class AddressDaoImpl extends AbstractDaoImpl<Address> implements AddressDao {

    private static final String SAVE = "insert into " + Tables.ADDRESSES +
            " (id, region, city, street, building, zip) " +
            "values (?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_REGION = "select * from " + Tables.ADDRESSES + " where region=?";
    private static final String FIND_BY_CITY = "select * from " + Tables.ADDRESSES + " where city=?";
    private static final String FIND_BY_STREET = "select * from " + Tables.ADDRESSES + " where street=?";
    private static final String FIND_BY_BUILDING = "select * from " + Tables.ADDRESSES + " where building=?";
    private static final String FIND_BY_ZIP = "select * from " + Tables.ADDRESSES + " where zip=?";
    private static final String UPDATE =  "update " + Tables.ADDRESSES +
            " set region=?, city=?, street=?, building=?, zip=? where id=?";

    public AddressDaoImpl() {
        super(RecordMapperSingleton.getInstance().getAddressRecordMapper(), Tables.ADDRESSES);
    }

    @Override
    public void save(Address address) throws DaoException {
        executeInsertQuery(SAVE, address.getId(), address.getRegion(), address.getCity(),
                address.getStreet(), address.getBuilding(), address.getZip());
    }

    @Override
    public List<Address> findByRegion(String region) throws DaoException {
        return executeQuery(FIND_BY_REGION, region);
    }

    @Override
    public List<Address> findByCity(String city) throws DaoException {
        return executeQuery(FIND_BY_CITY, city);
    }

    @Override
    public List<Address> findByStreet(String street) throws DaoException {
        return executeQuery(FIND_BY_STREET, street);
    }

    @Override
    public List<Address> findByBuilding(String building) throws DaoException {
        return executeQuery(FIND_BY_BUILDING, building);
    }

    @Override
    public List<Address> findByZip(String zip) throws DaoException {
        return executeQuery(FIND_BY_ZIP, zip);
    }

    @Override
    public void update(Address address) throws DaoException {
        executeInsertQuery(UPDATE, address.getRegion(), address.getCity(),
                address.getStreet(), address.getBuilding(), address.getZip(), address.getId());
    }
}
