package by.bsuir.service.impl;

import by.bsuir.dao.DaoFactory;
import by.bsuir.dao.service.BookDao;
import by.bsuir.domain.*;
import by.bsuir.service.AuthService;
import by.bsuir.service.LibraryService;
import by.bsuir.service.PostService;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class LibraryServiceImpl implements LibraryService {

    DaoFactory dao = DaoFactory.getInstance();
    BookDao bookDao = dao.getBookDao();

    /**
     * Количество книг на одной странице
     */
    private static final int BOOKS_PER_PAGE = 5;

    /**
     * Формат даты
     */
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
        book.setTitle(bookDao.escapeCyrillicSymbol(scanner.nextLine()));

        System.out.print("Введите описание книги: ");
        book.setDescription(bookDao.escapeCyrillicSymbol(scanner.nextLine()));

        System.out.print("Введите ФИО автора: ");
        book.setAuthor(bookDao.escapeCyrillicSymbol(scanner.nextLine()));

        System.out.print("Введите название компании-издателя: ");
        book.setPublisher(bookDao.escapeCyrillicSymbol(scanner.nextLine()));

        System.out.print("Введите идентификатор ISBN: ");
        book.setIsbn(bookDao.escapeCyrillicSymbol(scanner.nextLine()));

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
     * Метод для сохранения книги на сервер
     * @param authService Сущность подсистемы авторизации
     * @param postService Сущность подсистемы работы с почтой
     * @param book Объект сущности "Книга"
     * */
    private void saveBookToServer(AuthService authService, PostService postService, Book book) {
        bookDao.save(book);
        postService.notificationForUsers(authService);
    }

    /**
     * Метод для загрузки списка книг из JSON файла
     * @return List of books
     * */
    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll(Book.class);
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
        bookDao.update(book);
    }

    /**
     * Метод для обновления данных о книге на сервере
     * @param id Уникальный идентификатор
     * @param role Сущность перечисления "Роль"
     * @return SecurityCode
     * */
    @Override
    public SecurityCode removeBook(Integer id, Role role) {
        return bookDao.remove(id, role);
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
