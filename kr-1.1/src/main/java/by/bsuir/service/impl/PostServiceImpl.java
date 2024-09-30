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

    @Override
    public void notificationForUsers(AuthService authService) {
        List<String> emailAddresses = getEmailAddresses(authService);
        emailAddresses.forEach(email -> sendEmail(email, "КР 1.1", "Добавлена новая книга."));
    }

    @Override
    public void notificationForAdmin(Book book) {
        String messageBody = "Предложена новая книга: " + book.toString();
        sendEmail("olegolegolegoleg88@gmail.com", "КР 1.1", messageBody);
    }

    private List<String> getEmailAddresses(AuthService authService) {
        List<User> userList = authService.loadUsersFromApi();
        List<String> emailAddresses = new ArrayList<>();
        userList.forEach(userItem -> emailAddresses.add(userItem.getEmail()));
        return emailAddresses;
    }

    private void sendEmail(String recipient, String subject, String messageBody) {
        // Настройка свойств для подключения к SMTP-серверу
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.ru");   // SMTP-сервер
        props.put("mail.smtp.port", "465");            // Порт
        props.put("mail.smtp.auth", "true");           // Аутентификация
        props.put("mail.smtp.ssl.enable", "true");     // Включаем SSL для порта 465


        // Логин и пароль почтового ящика
        final String username = "shufladka";
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
            // Создание email-сообщения
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email, username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageBody);

            // Отправка email
            Transport.send(message);

            System.out.println("Email успешно отправлен!");

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Ошибка при отправке письма: " + e.getMessage(), e);
        }
    }
}
