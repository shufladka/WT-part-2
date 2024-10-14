package by.bsuir.service;

import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;

import java.io.IOException;

public interface MailService {
    void sendEmail(String recipient, String subject, String messageBody) throws ServiceException, DaoException, IOException;
}
