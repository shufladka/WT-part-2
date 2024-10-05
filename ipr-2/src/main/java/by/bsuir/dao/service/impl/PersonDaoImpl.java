package by.bsuir.dao.service.impl;

import by.bsuir.dao.AbstractDaoImpl;
import by.bsuir.dao.Tables;
import by.bsuir.dao.service.PersonDao;
import by.bsuir.entity.Person;
import by.bsuir.entity.Role;
import by.bsuir.exceptions.DaoException;
import by.bsuir.mapper.RecordMapperSingleton;

import java.time.LocalDate;
import java.util.List;

public class PersonDaoImpl extends AbstractDaoImpl<Person> implements PersonDao {

    private static final String SAVE = "insert into " + Tables.PEOPLE +
            " (id, username, password, first_name, last_name, birth_date, email, role_id) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_USERNAME = "select * from " + Tables.PEOPLE + " where username=?";
    private static final String FIND_BY_NAME = "select * from " + Tables.PEOPLE + " where first_name=? and last_name=?";
    private static final String FIND_BY_BIRTH_DATE = "select * from " + Tables.PEOPLE + " where birth_date=?";
    private static final String FIND_BY_EMAIL = "select * from " + Tables.PEOPLE + " where email=?";
    private static final String FIND_BY_ROLE = "select * from " + Tables.PEOPLE + " where role_id=?";
    private static final String UPDATE = "update " + Tables.PEOPLE +
            " set username=?, password=?, first_name=?, last_name=?, birth_date=?, email=?, role_id=? where id=?";

    public PersonDaoImpl() {
        super(RecordMapperSingleton.getInstance().getPersonRecordMapper(), Tables.PEOPLE);
    }

    @Override
    public void save(Person person, Role role) throws DaoException {
        executeInsertQuery(SAVE, person.getId(), person.getUsername(), person.getPassword(),
                person.getFirstName(), person.getLastName(), person.getBirthDate(),
                person.getEmail(), role.getId());
    }

    @Override
    public Person findByUsername(String username) throws DaoException {
        return executeQuerySingleton(FIND_BY_USERNAME, username);
    }

    @Override
    public List<Person> findByName(String firstName, String lastName) throws DaoException {
        return executeQuery(FIND_BY_NAME, firstName, lastName);
    }

    @Override
    public List<Person> findByBirthDate(LocalDate birthDate) throws DaoException {
        return executeQuery(FIND_BY_BIRTH_DATE, birthDate);
    }

    @Override
    public Person findByEmail(String email) throws DaoException {
        return executeQuerySingleton(FIND_BY_EMAIL, email);
    }

    @Override
    public List<Person> findByRole(Role role) throws DaoException {
        return executeQuery(FIND_BY_ROLE, role.getId());
    }

    @Override
    public void update(Person person, Role role) throws DaoException {
        executeInsertQuery(UPDATE, person.getUsername(), person.getPassword(),
                person.getFirstName(), person.getLastName(), person.getBirthDate(),
                person.getEmail(), role.getId(), person.getId());
    }
}
