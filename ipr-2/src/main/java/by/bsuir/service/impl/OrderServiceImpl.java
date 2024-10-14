package by.bsuir.service.impl;

import by.bsuir.dao.DaoSingleton;
import by.bsuir.dao.service.OrderDao;
import by.bsuir.entity.Order;
import by.bsuir.entity.Person;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.MailService;
import by.bsuir.service.OrderService;
import by.bsuir.service.PersonService;
import by.bsuir.service.ServiceSingleton;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    DaoSingleton dao = DaoSingleton.getInstance();
    OrderDao orderDao = dao.getOrderDao();

    @Override
    public void save(Person person, int roomId) throws DaoException, ServiceException, IOException {
        ServiceSingleton service = ServiceSingleton.getInstance();
        MailService mailService = service.getMailService();

        int id = 0;
        if (!orderDao.findAll().isEmpty()) {
            id = orderDao.getMaxById().getId() + 1;
        }

        orderDao.save(id, person.getId(), roomId);

        String subject = "Order № " + id;
        String messageBody =
                "<div class=\'card\' style=\'width: 40rem;\'>" +
                    "<div class=\'card-body\'>" +
                        "<h5 class=\'card-title\'>New order</h5>" +
                        "<p class='card-text'>Yahoo! You have created a new order!</p>" +
                        "<a href=\'http://localhost:8080/orders/" + id + "\' class=\'btn btn-primary\'>Let's check it!</a>" +
                    "</div>" +
                "</div>";

        mailService.sendEmail(person.getEmail(), subject, messageBody);
        }

        @Override
        public List<Order> findAll() throws ServiceException, DaoException {
            return orderDao.findAll();
        }

        @Override
        public List<Order> findByPersonId(int personId) throws DaoException {
            return orderDao.findByPersonId(personId);
        }

        @Override
        public List<Order> findByRoomId(int roomId) throws DaoException {
            return orderDao.findByRoomId(roomId);
        }

        @Override
        public List<Order> findByCreationDate(LocalDate from, LocalDate to) throws DaoException {
            return orderDao.findByCreationDate(from, to);
        }

        @Override
        public void update(Order order) throws ServiceException, DaoException, IOException {
            ServiceSingleton service = ServiceSingleton.getInstance();
            MailService mailService = service.getMailService();
            PersonService personService = service.getPersonService();

            orderDao.update(order);

            String subject = "Order № " + order.getId();
            String messageBody =
                    "<div class=\'card\' style=\'width: 40rem;\'>" +
                            "<div class=\'card-body\'>" +
                            "<h5 class=\'card-title\'>New changed in order № " + order.getId() + "</h5>" +
                            "<p class='card-text'>Your order was updated!</p>" +
                            "<a href=\'http://localhost:8080/orders/" + order.getId() + "\' class=\'btn btn-primary\'>Let's check it!</a>" +
                        "</div>" +
                    "</div>";

            mailService.sendEmail(personService.findById(order.getPersonId()).getEmail(), subject, messageBody);
        }

        @Override
        public void delete(int orderId) throws ServiceException, DaoException, IOException {
            ServiceSingleton service = ServiceSingleton.getInstance();
            MailService mailService = service.getMailService();
            PersonService personService = service.getPersonService();

            orderDao.delete(orderId);

            String subject = "Order № " + orderId;
            String messageBody =
                    "<div class=\'card\' style=\'width: 40rem;\'>" +
                            "<div class=\'card-body\'>" +
                            "<h5 class=\'card-title\'>New changed in order № " + orderId + "</h5>" +
                            "<p class='card-text'>Your order was closed!</p>" +
                            "<a href=\'http://localhost:8080/orders/" + orderId + "\' class=\'btn btn-primary\'>Let's check it!</a>" +
                        "</div>" +
                    "</div>";

            mailService.sendEmail(personService.findById(orderDao.findById(orderId).getPersonId()).getEmail(), subject, messageBody);
        }
    }
