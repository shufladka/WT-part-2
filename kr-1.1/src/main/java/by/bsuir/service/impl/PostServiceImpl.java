package by.bsuir.service.impl;

import by.bsuir.domain.User;
import by.bsuir.service.AuthService;
import by.bsuir.service.PostService;

import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements PostService {

    @Override
    public List<String> getEmailAddresses(AuthService authService) {
        List<User> userList = authService.loadUsersFromApi();
        List<String> emailAddresses = new ArrayList<>();
        userList.stream().forEach(userItem -> emailAddresses.add(userItem.getEmail()));
        return emailAddresses;
    }
}
