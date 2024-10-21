package by.bsuir.service;

public interface MenuService {
    void showAuthMenu(AuthService authService, LibraryService libraryService, PostService postService);
    void showMainMenu(AuthService authService, LibraryService libraryService, PostService postService);
}
