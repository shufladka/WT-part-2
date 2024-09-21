package by.bsuir.service;

import by.bsuir.domain.Book;
import by.bsuir.domain.Role;
import by.bsuir.domain.SecurityCode;

import java.util.List;

public interface LibraryService {
    void addBook(Role role);
    Book getBookById(Integer id);
    List<Book> getAllBooks();
    SecurityCode updateBook(Role role);
    SecurityCode removeBook(Integer id, Role role);
    void displayBooksWithPagination();
}
