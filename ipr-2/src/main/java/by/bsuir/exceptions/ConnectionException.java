package by.bsuir.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class ConnectionException extends SQLException {

    private static final Logger logger = LogManager.getLogger(ConnectionException.class);

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
        logger.error("[ConnectionException] {}", message);
    }

    public ConnectionException(String message) {
        super(message);
        logger.error("[ConnectionException] {}", message);
    }

    public ConnectionException() {
        super();
    }
}
