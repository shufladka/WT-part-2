package by.bsuir.service.impl;

import by.bsuir.dao.DaoFactory;
import by.bsuir.dao.service.PersonDao;
import by.bsuir.entity.Person;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.mapper.Attributes;
import by.bsuir.service.AuthService;
import by.bsuir.service.RoleService;
import by.bsuir.service.ServiceFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDate;
import java.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthServiceImpl implements AuthService {

    DaoFactory dao = DaoFactory.getInstance();
    PersonDao personDao = dao.getPersonDao();

    @Override
    public boolean registration(Person person) throws DaoException {
        if (!personDao.findAll().isEmpty()) {

            // Проверка регистрации на существующий почтовый адрес
            if (personDao.findByEmail(person.getEmail()) != null) {
                return false;
            }

            person.setId(personDao.getMaxById().getId() + 1);
        } else {
            person.setId(0);
        }

        person.setPassword(hashPassword(person.getPassword()));
        personDao.save(person);

        return true;
    }

    @Override
    public Person login(String username, String password) throws DaoException {
        Person person = personDao.findByUsername(username);
        if (person != null) {
            if (verifyPassword(password, person.getPassword())) {
                return person;
            }
        }
        return null;
    }

    @Override
    public void logout(Person person) throws ServiceException {

    }

    @Override
    public boolean isAdmin(Person person) throws ServiceException, DaoException {
        ServiceFactory service = ServiceFactory.getInstance();
        RoleService roleService = service.getRoleService();
        int adminRoleId = roleService.findAdminRoleId();

        if (person.getRoleId() != adminRoleId) {
            return false;
        } else {
            return true;
        }
    }

    // Метод для сериализации объекта Person с добавлением произвольных полей и хеширования в Base64
    @Override
    public String serializePersonBase64(Person person, Object... params) {

        // Создаем объект ObjectMapper для работы с JSON
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode personJson = objectMapper.createObjectNode();

        // Добавляем поля из объекта Person
        personJson.put(Attributes.ID, person.getId());
        personJson.put(Attributes.USERNAME, person.getUsername());
        personJson.put(Attributes.FIRST_NAME, person.getFirstName());
        personJson.put(Attributes.LAST_NAME, person.getLastName());
        personJson.put(Attributes.BIRTH_DATE, person.getBirthDate().toString());
        personJson.put(Attributes.EMAIL, person.getEmail());
        personJson.put(Attributes.ROLE_ID, person.getRoleId());

        // Проверяем параметры и добавляем их в JSON
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Неверное количество аргументов. Должны быть пары ключ-значение.");
        }

        for (int i = 0; i < params.length; i += 2) {
            String key = String.valueOf(params[i]);
            Object value = params[i + 1];
            personJson.putPOJO(key, value);
        }

        // Конвертируем ObjectNode в строку JSON
        try {
            String jsonString = objectMapper.writeValueAsString(personJson);

            // Преобразуем строку JSON в Base64
            return Base64.getEncoder().encodeToString(jsonString.getBytes(StandardCharsets.UTF_8));
        } catch (JsonProcessingException e) {

            // В случае ошибки выбрасываем исключение
            throw new RuntimeException("Ошибка сериализации объекта Person в JSON с добавлением параметров", e);
        }
    }

    // Метод для десериализации объекта Person из строки Base64
    @Override
    public Person deserializePersonBase64(String base64) {
        try {
            // Декодируем строку из Base64
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            String jsonString = new String(decodedBytes, StandardCharsets.UTF_8);

            // Создаем объект ObjectMapper для работы с JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Преобразуем строку JSON обратно в объект Person
            ObjectNode personJson = objectMapper.readValue(jsonString, ObjectNode.class);

            // Создаем новый объект Person и заполняем его данными из JSON
            Person person = new Person();
            person.setId(personJson.get(Attributes.ID).asInt());
            person.setUsername(personJson.get(Attributes.USERNAME).asText());
            person.setFirstName(personJson.get(Attributes.FIRST_NAME).asText());
            person.setLastName(personJson.get(Attributes.LAST_NAME).asText());
            person.setBirthDate(LocalDate.parse(personJson.get(Attributes.BIRTH_DATE).asText()));
            person.setEmail(personJson.get(Attributes.EMAIL).asText());
            person.setRoleId(personJson.get(Attributes.ROLE_ID).asInt());

            return person;

        } catch (Exception e) {
            throw new RuntimeException("Ошибка десериализации из Base64 в объект Person", e);
        }
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
}
