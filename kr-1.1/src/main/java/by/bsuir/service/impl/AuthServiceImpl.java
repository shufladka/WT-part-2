package by.bsuir.service.impl;

import by.bsuir.domain.Role;
import by.bsuir.domain.User;
import by.bsuir.service.AuthService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public class AuthServiceImpl implements AuthService {
    private User authentificatedUser = new User();
    private static final String credentialsUrl = "https://6a821cd8fdaa5103.mokky.dev/credentials";

    public AuthServiceImpl() {}

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
        user.setRole(Role.valueOf(roleInput.toUpperCase())); // Преобразование строки в enum Role

        System.out.print("\tВведите email: ");
        user.setEmail(scanner.nextLine());
        scanner.close();

        // Сохранение данных пользователя в файл на mokky.dev
        saveUserToApi(user);
    }

    private String escapeCyrillicSymbol(String json) {
        StringBuilder escapedJson = new StringBuilder();

        for (char c : json.toCharArray()) {

            // Если символ — кириллический, экранируем его
            if (Character.UnicodeScript.of(c) == Character.UnicodeScript.CYRILLIC) {
                escapedJson.append(String.format("\\u%04x", (int) c));
            } else {

                // В противном случае просто добавляем символ
                escapedJson.append(c);
            }
        }

        return escapedJson.toString();
    }

    // Функция для сохранения пользователя в файл на mokky.dev
    private void saveUserToApi(User user) {

        // Сериализация новой книги в JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(user);

        // Преобразование только кириллицы в Unicode
        String unicodeBookJson = escapeCyrillicSymbol(userJson);

        try {

            // Отправка новой книги на сервер
            int responseCode = getResponseCode(unicodeBookJson);
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Пользователь успешно добавлена на сервере.");
            } else {
                System.out.println("Ошибка при добавлении пользователя на сервере. Код ответа: " + responseCode);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при отправке пользователя на сервер", e);
        }
    }

    private static int getResponseCode(String unicodeUserJson) throws IOException {
        URL url = new URL(AuthServiceImpl.credentialsUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST"); // Используем POST для создания нового пользователя
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Отправка данных
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8))) {
            writer.write(unicodeUserJson);
            writer.flush();
        }

        // Проверка кода ответа от сервера
        return connection.getResponseCode();
    }

    // Функция для загрузки списка пользователей из JSON файла
    public List<User> loadUsersFromApi() {
        Gson gson = new Gson();

        try {
            // Открываем соединение для получения списка книг
            URL url = new URL(credentialsUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Проверяем код ответа от сервера
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                // Читаем JSON
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    return gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
                }
            } else {
                System.out.println("Ошибка: сервер вернул код " + responseCode);
                return List.of();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при получении данных: " + e.getMessage());
            return List.of();
        }
    }

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
