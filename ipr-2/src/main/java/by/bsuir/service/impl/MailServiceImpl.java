package by.bsuir.service.impl;

import by.bsuir.connection.ConnectionFactory;
import by.bsuir.service.MailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailServiceImpl implements MailService {

    private static final Logger logger = LogManager.getLogger(MailServiceImpl.class);
    private static final String MAIL_PROPERTIES = "mail.properties";

    @Override
    public void sendEmail(String recipient, String subject, String messageBody) throws IOException {

        // Настройка свойств для подключения к SMTP-серверу
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.mail.ru");   // SMTP-сервер
        properties.put("mail.smtp.port", "465");            // Порт
        properties.put("mail.smtp.auth", "true");           // Аутентификация
        properties.put("mail.smtp.ssl.enable", "true");     // Включаем SSL для порта 465

        Properties credentialProps = new Properties();
        credentialProps.load(ConnectionFactory.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES));

        // Логин и пароль почтового ящика
        final String email = credentialProps.getProperty("MAIL_ADDRESS");
        final String password = credentialProps.getProperty("MAIL_PASSWORD");

        // Создание сессии с аутентификацией
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {

            // Создание email-сообщения
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email, "BOOKIN"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(messageBody, "text/html; charset=UTF-8");

            // Отправка email
            Transport.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error("[MailServiceImpl] Error sending email" + e.getMessage());
        }
    }
}
