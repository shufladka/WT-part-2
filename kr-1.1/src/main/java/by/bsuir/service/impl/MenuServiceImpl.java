package by.bsuir.service.impl;

import by.bsuir.domain.Role;
import by.bsuir.service.AuthService;
import by.bsuir.service.LibraryService;
import by.bsuir.service.MenuService;
import by.bsuir.service.PostService;

import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    /**
     * Метод для отображения меню подсистемы авторизации
     * @param authService Сущность подсистемы авторизации
     * */
    @Override
    public void showAuthMenu(AuthService authService, LibraryService libraryService, PostService postService) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\t====== ВЫ НЕ АВТОРИЗОВАНЫ ======");
            System.out.println("\t1. Войти");
            System.out.println("\t2. Зарегистрироваться");
            System.out.println("\t0. Выйти");
            System.out.println("\t==================================");

            System.out.print("\n\tВведите пункт меню (1-2) или 0 для выхода: ");
            int selectedPage = scanner.nextInt();

            switch (selectedPage) {
                case 0:
                    System.out.println("\tВыход из приложения.");
                    return;
                case 1:
                    authService.login();
                    if (authService.isLoggedIn(authService.getAuthentificatedUser())) {
                        showMainMenu(authService, libraryService, postService);
                    }
                    break;
                case 2:
                    authService.registration();
                    break;
                default:
                    System.out.println("\tНеверный номер страницы, попробуйте снова.");
                    break;
            }
        }
    }

    /**
     * Метод для отображения главного меню
     * @param authService Сущность подсистемы авторизации
     * @param libraryService Сущность подсистемы библиотеки
     * @param postService Сущность подсистемы работы с почтой
     * */
    @Override
    public void showMainMenu(AuthService authService, LibraryService libraryService, PostService postService) {
        Scanner scanner = new Scanner(System.in);
        Role role = authService.getAuthentificatedUser().getRole();
        int selectedBook;

        while (true) {
            System.out.println("\n\t========== ГЛАВНОЕ МЕНЮ ==========");
            System.out.println("\t1. Просмотр всей библиотеки");
            System.out.println("\t2. Поиск книги по идентификатору");
            System.out.println("\t3. Предложить новую книгу");
            System.out.println("\t4. Обновить информацию о книге из библиотеки");
            System.out.println("\t5. Удалить книгу из библиотеки");
            System.out.println("\t0. Выйти из аккаунта");
            System.out.println("\t==================================");

            System.out.print("\n\tВведите пункт меню (1-5) или 0 для выхода: ");
            int selectedPage = scanner.nextInt();

            switch (selectedPage) {
                case 0:
                    authService.logout();
                    return;
                case 1:
                    libraryService.displayBooksWithPagination();
                    break;
                case 2:
                    System.out.print("\tВведите идентификатор интересующей книги: ");
                    selectedBook = scanner.nextInt();
                    libraryService.displayBookById(selectedBook);
                    break;
                case 3:
                    libraryService.addBook(authService, postService, role);
                    break;
                case 4:
                    System.out.print("\tВведите идентификатор интересующей книги: ");
                    selectedBook = scanner.nextInt();
                    libraryService.updateBook(selectedBook, authService, postService, role);
                    break;
                case 5:
                    System.out.print("\tВведите идентификатор интересующей книги: ");
                    selectedBook = scanner.nextInt();
                    libraryService.removeBook(selectedBook, role);
                    break;
                default:
                    System.out.println("\tНеверный номер страницы, попробуйте снова.");
                    break;
            }
        }
    }
}
