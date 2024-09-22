package by.bsuir.service;

import by.bsuir.domain.User;

import java.util.List;

public interface AuthService {
    void registration();
    void login();
    void logout();
    boolean isLoggedIn(User user);
    boolean isAdmin(User user);
    User getAuthentificatedUser();
    void setAuthentificatedUser(User authentificatedUser);
    List<User> loadUsersFromApi();
}
