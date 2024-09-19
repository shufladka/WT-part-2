package by.bsuir.service.impl;

import by.bsuir.domain.Role;
import by.bsuir.domain.User;
import by.bsuir.service.AuthService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthServiceImpl implements AuthService {
    private User authentificatedUser = new User();
    private static final String filePath = "kr-1.1/src/main/output/credentials.json";
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public AuthServiceImpl() {}

    @Override
    public void registration() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.print("Введите имя пользователя (username): ");
        user.setUsername(scanner.nextLine());

        System.out.print("Введите пароль: ");
        user.setPassword(hashPassword(scanner.nextLine()));

        System.out.print("Введите имя: ");
        user.setName(scanner.nextLine());

        System.out.print("Введите фамилию: ");
        user.setSurname(scanner.nextLine());

        System.out.print("Введите роль (например, ADMIN или USER): ");
        String roleInput = scanner.nextLine();
        user.setRole(Role.valueOf(roleInput.toUpperCase())); // Преобразование строки в enum Role

        System.out.print("Введите дату рождения (в формате ДД.ММ.ГГГГ): ");
        String dateOfBirthInput = scanner.nextLine();
        try {
            user.setDateOfBirth(dateFormat.parse(dateOfBirthInput));
        } catch (Exception e) {
            System.out.print("Неверный формат даты! Пожалуйста, повторите регистрацию.");
            return;
        }

        System.out.print("Введите email: ");
        user.setEmail(scanner.nextLine());
        scanner.close();

        // Сохранение данных пользователя в файл JSON
        saveUserToFile(user);
    }

    // Функция для сохранения пользователя в JSON файл
    private void saveUserToFile(User user) {
        List<User> users = loadUsersFromFile();

        // Добавляем нового пользователя в список
        users.add(user);

        try {
            // Проверяем и создаем директорию, если она не существует
            Path directoryPath = Paths.get("kr-1.1/src/main/output");
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);  // Создаем директории
            }

            // Сериализация данных в JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(users, writer);
                System.out.println("Пользователь успешно сохранен в файл.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Функция для загрузки списка пользователей из JSON файла
    private List<User> loadUsersFromFile() {
        Gson gson = new Gson();
        File file = new File("kr-1.1/src/main/output/credentials.json");

        // Если файл не существует, создаем новый список пользователей
        if (!file.exists()) {
            System.out.println("Файл не найден, создается новый файл.");
            return new ArrayList<>();
        }

        // Читаем файл, если он существует
        try (Reader reader = new FileReader(filePath)) {
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя пользователя (username): ");
        String username = scanner.nextLine();

        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        List<User> users = loadUsersFromFile();

        for (User userItem : users) {
            if (userItem.getUsername().equals(username)) {
                if (verifyPassword(password, userItem.getPassword())) {
                    System.out.println("атлишна");
                    setAuthentificatedUser(userItem);
                }
            }
        }
    }

    @Override
    public void logout() {
        authentificatedUser = null;
    }

    @Override
    public boolean isLoggedIn(User user) {
        return authentificatedUser.equals(user);
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getRole().equals(Role.ADMIN);
    }

    // Метод для хеширования пароля с использованием SHA-256
    private static String hashPassword(String password) {
        try {
            // Получаем экземпляр алгоритма SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Преобразуем пароль в байты и хешируем его
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Преобразуем хешированный пароль в строку
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка хеширования пароля", e);
        }
    }

    // Вспомогательный метод для преобразования байтов в шестнадцатеричную строку
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

    // Проверка пароля
    public static boolean verifyPassword(String enteredPassword, String storedHash) {
        String hashedEnteredPassword = hashPassword(enteredPassword);
        return hashedEnteredPassword.equals(storedHash);
    }

    @Override
    public User getAuthentificatedUser() {
        return authentificatedUser;
    }

    public void setAuthentificatedUser(User authentificatedUser) {
        this.authentificatedUser = authentificatedUser;
    }
}
