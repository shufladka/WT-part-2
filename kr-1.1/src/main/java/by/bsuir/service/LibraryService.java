package by.bsuir.service;

import by.bsuir.domain.Book;

import java.util.List;

public interface LibraryService {
    void addBook();
    Book getBookById(Integer id);
    List<Book> getAllBooks();
    void updateBook();
    void removeBook(Integer id);
}
