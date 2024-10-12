package by.bsuir.exceptions;

public class DaoException extends Exception {

    public DaoException(String message, Throwable reason) {
        super(message, reason);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException() {}
}
