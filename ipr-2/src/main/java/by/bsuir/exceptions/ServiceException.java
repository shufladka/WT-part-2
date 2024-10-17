package by.bsuir.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceException extends Exception {

    private static final Logger logger = LogManager.getLogger(ServiceException.class);

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        logger.error("[ServiceException] {}", message);
    }

    public ServiceException(String message) {
        super(message);
        logger.error("[ServiceException] {}", message);
    }

    public ServiceException() {
        super();
    }
}
