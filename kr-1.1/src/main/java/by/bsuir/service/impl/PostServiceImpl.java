package by.bsuir.service.impl;

import by.bsuir.domain.Book;
import by.bsuir.domain.User;
import by.bsuir.service.AuthService;
import by.bsuir.service.PostService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PostServiceImpl implements PostService {

    /**
     * Метод для рассылки уведомлений пользователям
     * @param authService Объект системы авторизации
     */
    @Override
    public void notificationForUsers(AuthService authService) {
        List<String> emailAddresses = getEmailAddresses(authService);
        emailAddresses.forEach(email -> sendEmail(email, "Добавлена новая книга."));
    }

    /**
     * Метод для отправки уведомления администратору о добавлении новой книги
     * @param book Объект книги
     */
    @Override
    public void notificationForAdmin(Book book) {
        String messageBody = "Предложена новая книга: " + book.toString();
        sendEmail("olegolegolegoleg88@gmail.com", messageBody);
    }

    /**
     * Метод для получения списка электронных адресов пользователей
     * @param authService Объект системы авторизации
     * @return List of String
     */
    private List<String> getEmailAddresses(AuthService authService) {
        List<User> userList = authService.loadUsersFromApi();
        List<String> emailAddresses = new ArrayList<>();
        userList.forEach(userItem -> emailAddresses.add(userItem.getEmail()));
        return emailAddresses;
    }

    /**
     * Метод для отправки письма на электронную почту
     *
     * @param recipient Адрес получателя
     * @param messageBody Тело письма
     */
    private void sendEmail(String recipient, String messageBody) {
        // Настройка свойств для подключения к SMTP-серверу
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.ru");   // SMTP-сервер
        props.put("mail.smtp.port", "465");            // Порт
        props.put("mail.smtp.auth", "true");           // Аутентификация
        props.put("mail.smtp.ssl.enable", "true");     // Включаем SSL для порта 465

        // Логин и пароль почтового ящика
        final String username = "KR-1.1";
        final String email = "oleg_7301523@mail.ru";
        final String password = "4zrN5X8TTmy4QL8TE0rt";

        // Создание сессии с аутентификацией
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email, username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("LIBRARY");
            message.setText(messageBody);

            Transport.send(message);
            System.out.println("Email успешно отправлен!");

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Ошибка при отправке письма: " + e.getMessage(), e);
        }
    }
}
