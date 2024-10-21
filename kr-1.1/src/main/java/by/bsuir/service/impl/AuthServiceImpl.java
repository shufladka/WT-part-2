package by.bsuir.service.impl;

import by.bsuir.dao.DaoFactory;
import by.bsuir.dao.service.UserDao;
import by.bsuir.domain.Role;
import by.bsuir.domain.User;
import by.bsuir.service.AuthService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public class AuthServiceImpl implements AuthService {

    DaoFactory dao = DaoFactory.getInstance();
    UserDao userDao = dao.getUserDao();

    /**
     * Объект авторизованного пользователя
     */
    private User authentificatedUser = null;

    public AuthServiceImpl() {}

    /**
     * Метод для регистрации в системе
     * */
    @Override
    public void registration() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.print("\n\tВведите имя пользователя (username): ");
        user.setUsername(scanner.nextLine());

        System.out.print("\tВведите пароль: ");
        user.setPassword(hashPassword(scanner.nextLine()));

        System.out.print("\tВведите имя: ");
        user.setName(scanner.nextLine());

        System.out.print("\tВведите фамилию: ");
        user.setSurname(scanner.nextLine());

        System.out.print("\tВведите роль (например, ADMIN или USER): ");
        String roleInput = scanner.nextLine();
        user.setRole(Role.valueOf(roleInput.toUpperCase()));

        System.out.print("\tВведите email: ");
        user.setEmail(scanner.nextLine());

        saveUserToApi(user);
    }

    /**
     * Метод для сохранения пользователя в файл на mokky.dev
     * @param user Сущность пользователя
     * */
    private void saveUserToApi(User user) {
        userDao.save(user);
    }

    /**
     * Метод для загрузки списка пользователей из JSON файла
     * @return List of users
     * */
    public List<User> loadUsersFromApi() {
        return userDao.findAll(User.class);
    }

    /**
     * Метод для авторизации в системе
     * */
    @Override
    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\tВведите имя пользователя (username): ");
        String username = scanner.nextLine();

        System.out.print("\tВведите пароль: ");
        String password = scanner.nextLine();

        List<User> users = loadUsersFromApi();

        users.stream()
                .filter(userItem -> userItem.getUsername().equals(username))
                .filter(userItem -> verifyPassword(password, userItem.getPassword()))
                .forEach(this::setAuthentificatedUser);
    }

    /**
     * Метод для выхода из аккаунта
     * */
    @Override
    public void logout() {
        authentificatedUser = null;
    }

    /**
     * Метод для проверки наличия авторизации в системе
     * @param user Сущность пользователя
     * @return void
     * */
    @Override
    public boolean isLoggedIn(User user) {
        return authentificatedUser.equals(user);
    }

    /**
     * Метод для проверки наличия прав администратора
     * @param user Сущность пользователя
     * @return void
     * */
    @Override
    public boolean isAdmin(User user) {
        return user.getRole().equals(Role.ADMIN);
    }

    /**
     * Метод для хеширования пароля с использованием SHA-256
     * @param password Пароль пользователя
     * @return String
     * */
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка хеширования пароля", e);
        }
    }

    /**
     * Вспомогательный метод для преобразования байтов в шестнадцатеричную строку
     * @param hash Хэш-код пароля
     * @return static String
     * */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Метод для проверки пароля
     * @return boolean
     * */
    public boolean verifyPassword(String enteredPassword, String storedHash) {
        String hashedEnteredPassword = hashPassword(enteredPassword);
        return hashedEnteredPassword.equals(storedHash);
    }

    /**
     * Метод для получения сущности авторизированного пользователя
     * @return User
     * */
    @Override
    public User getAuthentificatedUser() {
        return authentificatedUser;
    }

    /**
     * Метод для установления сущности авторизированного пользователя
     * @param authentificatedUser Сущность авторизованного пользователя
     * */
    public void setAuthentificatedUser(User authentificatedUser) {
        this.authentificatedUser = authentificatedUser;
    }
}
