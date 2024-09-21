package by.bsuir.service.impl;

import by.bsuir.domain.*;
import by.bsuir.service.LibraryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LibraryServiceImpl implements LibraryService {
    private static final int BOOKS_PER_PAGE = 5; // Количество книг на одной странице
    private static final String libraryUrl = "https://6a821cd8fdaa5103.mokky.dev/library";
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void addBook(Role role) {
        setBooksFields(role, OperationType.CREATION);
    }

    // Функция для заполнения полей книги (вводить только латиницей)
    private void setBooksFields(Role role, OperationType operationType) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Book book = new Book();

        System.out.print("Введите название книги (заголовок): ");
        book.setTitle(escapeCyrillicSymbol(scanner.nextLine()));

        System.out.print("Введите описание книги: ");
        book.setDescription(escapeCyrillicSymbol(scanner.nextLine()));

        System.out.print("Введите ФИО автора: ");
        book.setAuthor(escapeCyrillicSymbol(scanner.nextLine()));

        System.out.print("Введите название компании-издателя: ");
        book.setPublisher(escapeCyrillicSymbol(scanner.nextLine()));

        System.out.print("Введите идентификатор ISBN: ");
        book.setIsbn(escapeCyrillicSymbol(scanner.nextLine()));

        System.out.print("Введите количество страниц: ");
        String pages = scanner.nextLine();
        book.setPages(Integer.valueOf(pages));

        System.out.print("Введите название жанра (например, DETECTIVE, ROMANCE\n" +
                "SCIENCE_FICTION, FANTASY, COMEDY, HISTORICAL): ");
        String genreInput = scanner.nextLine();
        book.setGenre(Genre.valueOf(genreInput.toUpperCase()));

        System.out.print("Введите дату публикации (в формате ДД.ММ.ГГГГ): ");
        String publicationDateInput = scanner.nextLine();
        try {
            book.setPublicationDate(dateFormat.parse(publicationDateInput));
        } catch (Exception e) {
            System.out.println("Неверный формат даты! Пожалуйста, повторите регистрацию.");
            return;
        }

        System.out.print("Введите тип носителя (например, PAPER или ELECTRONIC): ");
        String bookTypeInput = scanner.nextLine();
        book.setBookType(BookType.valueOf(bookTypeInput.toUpperCase()));
        
        if (role.equals(Role.ADMIN)) {

            // Добавляем новую книгу в библиотеку
            if (operationType.equals(OperationType.CREATION)) {
                saveBookToServer(book);
            } else

                // Обновляем поля книги
                if (operationType.equals(OperationType.UPDATE)) {
                updateBookOnServer(book);
            }
        }
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

    private void saveBookToServer(Book book) {

        // Сериализация новой книги в JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String bookJson = gson.toJson(book);

        // Преобразование только кириллицы в Unicode
        String unicodeBookJson = escapeCyrillicSymbol(bookJson);

        try {
            
            // Отправка новой книги на сервер
            int responseCode = getResponseCode(libraryUrl, "POST", unicodeBookJson);
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Книга успешно добавлена на сервере.");
            } else {
                System.out.println("Ошибка при добавлении книги на сервере. Код ответа: " + responseCode);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при отправке книги на сервер", e);
        }
    }

    private static int getResponseCode(String libraryUrl, String POST, String unicodeBookJson) throws IOException {
        URL url = new URL(libraryUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(POST); // Используем POST для создания новой книги
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Отправка данных
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8))) {
            writer.write(unicodeBookJson);
            writer.flush();
        }

        // Проверка кода ответа от сервера
        return connection.getResponseCode();
    }

    @Override
    public Book getBookById(Integer id) {
        List<Book> books = getAllBooks();
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    // Функция для загрузки списка книг из JSON файла
    @Override
    public List<Book> getAllBooks() {
        Gson gson = new Gson();

        try {
            // Открываем соединение для получения списка книг
            URL url = new URL(libraryUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Проверяем код ответа от сервера
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                
                // Читаем JSON
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    return gson.fromJson(reader, new TypeToken<List<Book>>() {}.getType());
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
    public SecurityCode updateBook(Role role) {

        // Операция доступна только Администратору
        if (role.equals(Role.ADMIN)) {
            setBooksFields(role, OperationType.UPDATE);
            return SecurityCode.ALLOWED;
        }

        System.out.println("\tОперация доступна только администратору.");
        return SecurityCode.DENIED;
    }

    private void updateBookOnServer(Book book) {
        try {
            
            // Сериализация данных в JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String bookJson = gson.toJson(book);

            // Формируем URL для обновления книги по ID
            String urlString = libraryUrl + book.getId();
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
    public SecurityCode removeBook(Integer id, Role role) {

        // Операция доступна только Администратору
        if (role.equals(Role.ADMIN)) {
            try {
                // Формируем URL для удаления книги по ID
                String urlString = libraryUrl + id;
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

    // Функция для вывода книг с постраничным просмотром (пагинацией)
    public void displayBooksWithPagination() {
        List<Book> books = getAllBooks();

        if (books.isEmpty()) {
            System.out.println("Книги отсутствуют.");
            return;
        }

        int totalPages = (int) Math.ceil((double) books.size() / BOOKS_PER_PAGE);
        Scanner scanner = new Scanner(System.in);

        int currentPage = 1;
        while (true) {
            
            // Выводим книги на текущей странице
            displayPage(books, currentPage, totalPages);

            System.out.println("\n\tВведите номер страницы (1-" + totalPages + ") или 0 для выхода:");
            int selectedPage = scanner.nextInt();

            if (selectedPage == 0) {
                System.out.println("\tВыход из постраничного просмотра.");
                break;
            } else if (selectedPage >= 1 && selectedPage <= totalPages) {
                currentPage = selectedPage;
            } else {
                System.out.println("\tНеверный номер страницы, попробуйте снова.");
            }
        }
    }

    // Функция для вывода одной страницы
    private void displayPage(List<Book> books, int currentPage, int totalPages) {
        System.out.println("\n\tСтраница " + currentPage + " из " + totalPages + ":");

        int start = (currentPage - 1) * BOOKS_PER_PAGE;
        int end = Math.min(start + BOOKS_PER_PAGE, books.size());

        for (int i = start; i < end; i++) {
            Book book = books.get(i);
            System.out.println("\t" + book.getId() + ". \"" + book.getTitle() + "\",");
            System.out.println("\t\tАвтор: " + book.getAuthor() + ",");
            System.out.println("\t\tОписание: " + "\"" + book.getDescription() + "\",");
            System.out.println("\t\tЖанр: " + book.getGenre().getName() + ",");
            System.out.println("\t\tНоситель: " + book.getBookType().getDescription() + ",");
            System.out.println("\t\tОпубликовал: " + book.getPublisher() + ",");
            System.out.println("\t\tОпубликовано: " + book.getPublicationDate() + ",");
            System.out.println("\t\tISBN: " + book.getIsbn() + ",");
            System.out.println("\t\tСтраниц: " + book.getPages() + ".");
        }
    }
}
