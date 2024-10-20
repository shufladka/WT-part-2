package by.bsuir.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class AbstractDaoImpl<T> implements Dao<T> {

    private final String link;

    public AbstractDaoImpl(String link) {
        this.link = link;
    }

    @Override
    public void save(T t) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(t);

        // Преобразование только кириллицы в Unicode
        String unicodeJson = escapeCyrillicSymbol(userJson);

        try {

            // Отправка новой книги на сервер
            int responseCode = getResponseCode(link,"POST", unicodeJson);
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Запись успешно добавлена на сервер.");
            } else {
                System.out.println("Ошибка при добавлении записи на сервер. Код ответа: " + responseCode);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при отправке записи на сервер", e);
        }
    }

    @Override
    public List<T> findAll() {
        Gson gson = new Gson();

        try {
            // Открываем соединение для получения списка книг
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Проверяем код ответа от сервера
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                // Читаем JSON
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    return gson.fromJson(reader, new TypeToken<List<T>>() {}.getType());
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

    /**
     * Метод для получения целочисленного ответа от сервера
     * @param urlLink Ссылка на ресурс
     * @param method Тип метода HTTP
     * @param unicodeJson Сериализированные данные
     * @return static int
     * */
    public int getResponseCode(String urlLink, String method, String unicodeJson) throws IOException {

        URL url = new URL(urlLink);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Отправка данных
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8))) {
            writer.write(unicodeJson);
            writer.flush();
        }

        // Проверка кода ответа от сервера
        return connection.getResponseCode();
    }

    /**
     * Метод для экранирования кириллицы в ответе формата JSON
     * @param json Исходная строка
     * @return String
     * */
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
}
