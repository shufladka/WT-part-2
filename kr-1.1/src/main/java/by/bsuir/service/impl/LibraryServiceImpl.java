package by.bsuir.service.impl;

import by.bsuir.domain.*;
import by.bsuir.service.LibraryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryServiceImpl implements LibraryService {

    private static final String filePath = "kr-1.1/src/main/output/library.json";
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void addBook() {
        setBooksFields(true);
    }

    private void setBooksFields(boolean isNewFile) {
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();

        System.out.print("Введите название книги (заголовок): ");
        book.setTitle(scanner.nextLine());

        System.out.print("Введите описание книги: ");
        book.setDescription(scanner.nextLine());

        System.out.print("Введите ФИО автора: ");
        book.setAuthor(scanner.nextLine());

        System.out.print("Введите название компании-издателя: ");
        book.setPublisher(scanner.nextLine());

        System.out.print("Введите идентификатор ISBN: ");
        book.setIsbn(scanner.nextLine());

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

        // Добавляем новую книгу в библиотеку
        saveBookToFile(book, isNewFile);
    }

    // Функция для сохранения книги в JSON файл
    private void saveBookToFile(Book book, boolean isNewRecord) {
        List<Book> books = getAllBooks();

        // Присваиваем индекс последней добавленной книге
        if (isNewRecord) {
            book.setId(getLastBookIndex(books));
        }

        // Добавляем новую книгу в список
        books.add(book);

        try {
            // Проверяем и создаем директорию, если она не существует
            Path directoryPath = Paths.get("kr-1.1/src/main/output");
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);  // Создаем директории
            }

            // Сериализация данных в JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(books, writer);
                System.out.println("Пользователь успешно сохранен в файл.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Функция получения индекса последней по списку книги
    private Integer getLastBookIndex(List<Book> books) {
        int index = 0;

        if (!books.isEmpty()) {
           index = (books.get(books.size() - 1).getId()) + 1;
        }

        return index;
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
        File file = new File(filePath);

        // Если файл не существует, создаем новый список пользователей
        if (!file.exists()) {
            System.out.println("Файл не найден, создается новый файл.");
            return new ArrayList<>();
        }

        // Читаем файл, если он существует
        try (Reader reader = new FileReader(filePath)) {
            Type bookListType = new TypeToken<ArrayList<Book>>() {}.getType();
            return gson.fromJson(reader, bookListType);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void updateBook() {
        setBooksFields(false);
    }

    @Override
    public void removeBook(Integer id) {

    }
}
