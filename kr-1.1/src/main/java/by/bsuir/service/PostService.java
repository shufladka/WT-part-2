package by.bsuir.service;

import by.bsuir.domain.Book;

public interface PostService {
    void notificationForUsers(AuthService authService);
    void notificationForAdmin(Book book);
}
