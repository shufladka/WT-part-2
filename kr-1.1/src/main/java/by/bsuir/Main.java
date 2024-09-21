package by.bsuir;

import by.bsuir.domain.Role;
import by.bsuir.service.AuthService;
import by.bsuir.service.LibraryService;
import by.bsuir.service.MenuService;
import by.bsuir.service.impl.AuthServiceImpl;
import by.bsuir.service.impl.LibraryServiceImpl;
import by.bsuir.service.impl.MenuServiceImpl;

//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;

/*
* Задание: создать однопоточное консольное приложение “Учет книг в домашней библиотеке”
* Общие требования к заданию:
* · Система учитывает книги как в электронном, так и в бумажном варианте.
* · Существующие роли: пользователь, администратор.
* · Пользователь может просматривать книги в каталоге книг, осуществлять поиск книг в каталоге.
* · Администратор может модифицировать каталог.
* · *При добавлении описания книги в каталог оповещение о ней рассылается на e-mail всем пользователям
* · **При просмотре каталога желательно реализовать постраничный просмотр
* · ***Пользователь может предложить добавить книгу в библиотеку, переслав её администратору на e-mail.
* · Каталог книг хранится в текстовом файле
* · Данные аутентификации пользователей хранятся в текстовом файле. Пароль не хранится в открытом виде.
* */
public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthServiceImpl();
        MenuService menuService = new MenuServiceImpl();
        LibraryService libraryService = new LibraryServiceImpl();
//        menuService.showAuthMenu();
//        authService.registration();
        //authService.login();
        //libraryService.displayBooksWithPagination();
        libraryService.addBook(Role.ADMIN);
    }
}