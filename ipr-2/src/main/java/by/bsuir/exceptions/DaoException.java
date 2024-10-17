package by.bsuir.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoException extends Exception {

    private static final Logger logger = LogManager.getLogger(ConnectionException.class);

    public DaoException(String message, Throwable reason) {
        super(message, reason);
        logger.error("[DaoException] {}", message);
    }

    public DaoException(String message) {
        super(message);
        logger.error("[ConnectionException] {}", message);
    }

    public DaoException() {}
}
