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
    private static final int BOOKS_PER_PAGE = 5; // Количество книг на одной странице
    private static final String filePath = "kr-1.1/src/main/output/library.json";
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void addBook(Role role) {
        setBooksFields(true, role);
    }

    private void setBooksFields(boolean isNewFile, Role role) {
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
        if (role.equals(Role.ADMIN)) {
            saveBookToFile(book, isNewFile);
        }
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
    public SecurityCode updateBook(Role role) {

        // Операция доступна только Администратору
        if (role.equals(Role.ADMIN)) {
            setBooksFields(false, role);
            return SecurityCode.ALLOWED;
        }

        System.out.println("\tОперация доступна только администратору.");
        return SecurityCode.DENIED;
    }

    @Override
    public SecurityCode removeBook(Integer id, Role role) {

        // Операция доступна только Администратору
        if (role.equals(Role.ADMIN)) {

            // Получаем список всех книг
            List<Book> books = getAllBooks();

            // Находим и удаляем книгу по ID
            boolean removed = books.removeIf(book -> book.getId().equals(id));

            if (removed) {
                try {
                    // Сериализация обновленного списка книг в JSON
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    try (FileWriter writer = new FileWriter(filePath)) {
                        gson.toJson(books, writer);
                        System.out.println("Книга успешно удалена.");
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка при сохранении файла после удаления книги", e);
                }
            } else {
                System.out.println("Книга с ID " + id + " не найдена.");
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
            System.out.println("\t" + (i + 1) + ". \"" + book.getTitle() + "\",");
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
