package by.bsuir.dao.service.impl;

import by.bsuir.dao.AbstractDaoImpl;
import by.bsuir.dao.Links;
import by.bsuir.dao.service.BookDao;
import by.bsuir.domain.Book;
import by.bsuir.domain.Role;
import by.bsuir.domain.SecurityCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookDaoImpl extends AbstractDaoImpl<Book> implements BookDao {

    public BookDaoImpl() {
        super(Links.BOOKS);
    }

    @Override
    public void update(Book book) {
        try {

            // Сериализация данных в JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String bookJson = gson.toJson(book);

            // Формируем URL для обновления книги по ID
            String urlString = Links.BOOKS + book.getId();
            int responseCode = getResponseCode(urlString, "PATCH", bookJson);
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                System.out.println("Книга успешно обновлена на сервере.");
            } else {
                System.out.println("Ошибка при обновлении книги на сервере. Код ответа: " + responseCode);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при обновлении книги на сервере", e);
        }
    }

    @Override
    public SecurityCode remove(Integer id, Role role) {

        // Операция доступна только Администратору
        if (role.equals(Role.ADMIN)) {
            try {
                // Формируем URL для удаления книги по ID
                String urlString = Links.BOOKS + id;
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");

                // Проверка кода ответа от сервера
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                    System.out.println("Книга с ID " + id + " успешно удалена.");
                } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    System.out.println("Книга с ID " + id + " не найдена на сервере.");
                } else {
                    System.out.println("Ошибка при удалении книги. Код ответа: " + responseCode);
                }
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при удалении книги с сервера", e);
            }

            return SecurityCode.ALLOWED;
        }

        System.out.println("\tОперация доступна только администратору.");
        return SecurityCode.DENIED;
    }
}
