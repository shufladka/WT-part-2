package by.bsuir.service.impl;

import by.bsuir.domain.*;
import by.bsuir.service.AuthService;
import by.bsuir.service.LibraryService;
import by.bsuir.service.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class LibraryServiceImpl implements LibraryService {
    private static final int BOOKS_PER_PAGE = 5; // Количество книг на одной странице
    private static final String libraryUrl = "https://6a821cd8fdaa5103.mokky.dev/library/";
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Метод для добавления книги в библиотеку
     * @param authService Сущность подсистемы авторизации
     * @param postService Сущность подсистемы работы с почтой
     * @param role Сущность перечисления "Роль"
     * */
    @Override
    public void addBook(AuthService authService, PostService postService, Role role) {
        Book book = new Book();
        setBooksFields(book, authService, postService, role, OperationType.CREATION);
    }

    /**
     * Метод для заполнения полей книги (вводить только латиницей)
     * @param book Объект сущности "Книга"
     * @param authService Сущность подсистемы авторизации
     * @param postService Сущность подсистемы работы с почтой
     * @param role Сущность перечисления "Роль"
     * @param operationType Сущность перечисления "Тип операции"
     * */
    private void setBooksFields(Book book, AuthService authService, PostService postService,
                                Role role, OperationType operationType) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

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
                saveBookToServer(authService, postService, book);
            } else

                // Обновляем поля книги
                if (operationType.equals(OperationType.UPDATE)) {
                updateBookOnServer(book);
            }
        } else {
            if (role.equals(Role.USER)) {

                // Уведомляем администратора о просьбе добавить новую книгу
                postService.notificationForAdmin(book);
            }
        }
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

    /**
     * Метод для сохранения книги на сервер
     * @param authService Сущность подсистемы авторизации
     * @param postService Сущность подсистемы работы с почтой
     * @param book Объект сущности "Книга"
     * */
    private void saveBookToServer(AuthService authService, PostService postService, Book book) {

        // Сериализация новой книги в JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String bookJson = gson.toJson(book);

        // Преобразование только кириллицы в Unicode
        String unicodeBookJson = escapeCyrillicSymbol(bookJson);

        try {
            
            // Отправка новой книги на сервер
            int responseCode = getResponseCode(libraryUrl, "POST", unicodeBookJson);
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                postService.notificationForUsers(authService);
                System.out.println("Книга успешно добавлена на сервере.");
            } else {
                System.out.println("Ошибка при добавлении книги на сервере. Код ответа: " + responseCode);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при отправке книги на сервер", e);
        }
    }

    /**
     * Метод для получения целочисленного ответа от сервера
     * @param libraryUrl Ссылка на ресурс с книгами
     * @param method Тип метода HTTP
     * @param unicodeBookJson Сериализированные данные пользователя
     * @return static int
     * */
    private static int getResponseCode(String libraryUrl, String method, String unicodeBookJson) throws IOException {
        URL url = new URL(libraryUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method); // Используем POST для создания новой книги
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

    /**
     * Метод для загрузки списка книг из JSON файла
     * @return List of books
     * */
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

    /**
     * Метод для получения книги из всего списка книг по её уникальному идентификатору
     * @return List of books
     * */
    @Override
    public Book getBookById(Integer id) {
        List<Book> books = getAllBooks();
        return books.get(id);
    }

    /**
     * Метод для сохранения книги на сервер
     * @param id Уникальный идентификатор
     * @param authService Сущность подсистемы авторизации
     * @param postService Сущность подсистемы работы с почтой
     * @param role Сущность перечисления "Роль"
     * @return SecurityCode
     * */
    @Override
    public SecurityCode updateBook(Integer id, AuthService authService, PostService postService, Role role) {

        // Операция доступна только Администратору
        if (role.equals(Role.ADMIN)) {
            Book book = getBookById(id);
            setBooksFields(book, authService, postService, role, OperationType.UPDATE);
            return SecurityCode.ALLOWED;
        }

        System.out.println("\tОперация доступна только администратору.");
        return SecurityCode.DENIED;
    }

    /**
     * Метод для обновления данных о книге на сервере
     * @param book Объект сущности "Книга"
     * */
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

    /**
     * Метод для обновления данных о книге на сервере
     * @param id Уникальный идентификатор
     * @param role Сущность перечисления "Роль"
     * @return SecurityCode
     * */
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

    /**
     * Метод для вывода книг с постраничным просмотром (пагинацией)
     * */
    @Override
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

            System.out.print("\n\tВведите номер страницы (1-" + totalPages + ") или 0 для выхода: ");
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

    /**
     * Метод для конкретной книги по её идентификатору
     * */
    @Override
    public void displayBookById(int id) {
        Book book = getBookById(id);
        LocalDate localDate = Instant.ofEpochMilli(book.getPublicationDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int year = localDate.getYear();

        displayBook(book, year);
    }

    /**
     * Метод для вывода одной страницы с книгами (с пагинацией)
     * @param books Список объектов сущности "Книга"
     * @param currentPage Идентификатор текущей страницы
     * @param totalPages Идентификатор общего числа страниц
     * */
    private void displayPage(List<Book> books, int currentPage, int totalPages) {
        System.out.println("\n\tСтраница " + currentPage + " из " + totalPages + ": ");

        int start = (currentPage - 1) * BOOKS_PER_PAGE;
        int end = Math.min(start + BOOKS_PER_PAGE, books.size());

        for (int i = start; i < end; i++) {
            Book book = books.get(i);

            LocalDate localDate = Instant.ofEpochMilli(book.getPublicationDate().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            int year = localDate.getYear();

            displayBook(book, year);
        }
    }

    /**
     * Метод для вывода одной книги
     * @param book Объект сущности "Книга"
     * @param year Идентификатор года
     * */
    private void displayBook(Book book, int year) {
        System.out.println("\n\t" + book.getId() + ". \"" + book.getTitle() + "\",");
        System.out.println("\tАвтор: " + book.getAuthor() + ",");
        System.out.println("\tОписание: " + "\"" + book.getDescription() + "\",");
        System.out.println("\tЖанр: " + book.getGenre().getName() + ",");
        System.out.println("\tНоситель: " + book.getBookType().getDescription() + ",");
        System.out.println("\tОпубликовал: " + book.getPublisher() + ",");
        System.out.println("\tОпубликовано: " + year + ",");
        System.out.println("\tISBN: " + book.getIsbn() + ",");
        System.out.println("\tСтраниц: " + book.getPages() + ".");
    }
}
