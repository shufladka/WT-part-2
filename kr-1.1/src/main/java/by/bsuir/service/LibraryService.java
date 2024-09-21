package by.bsuir.service;

import by.bsuir.domain.Book;
import by.bsuir.domain.Role;
import by.bsuir.domain.SecurityCode;

import java.util.List;

public interface LibraryService {
    void addBook(AuthService authService, PostService postService, Role role);
    Book getBookById(Integer id);
    List<Book> getAllBooks();
    SecurityCode updateBook(AuthService authService, PostService postService, Role role);
    SecurityCode removeBook(Integer id, Role role);
    void displayBooksWithPagination();
}
