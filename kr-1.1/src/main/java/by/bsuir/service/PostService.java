package by.bsuir.service;

import by.bsuir.domain.User;

import java.util.List;

public interface PostService {
    List<String> getEmailAddresses(AuthService authService);
    void sendEmail(String recipient, String subject, String messageBody);
}
