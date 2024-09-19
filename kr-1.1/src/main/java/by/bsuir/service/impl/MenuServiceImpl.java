package by.bsuir.service.impl;

import by.bsuir.service.MenuService;

public class MenuServiceImpl implements MenuService {

    @Override
    public void showAuthMenu() {
        System.out.println("\n\t====== ВЫ НЕ АВТОРИЗОВАНЫ ======");
        System.out.println("\t1. Войти");
        System.out.println("\t2. Зарегистрироваться");
        System.out.println("\t3. Пожаловаться ");
        System.out.println("\t==================================");
    }

    @Override
    public void showMainMenu() {
        System.out.println("\n\t========== ГЛАВНОЕ МЕНЮ ==========");
        System.out.println("\t1. Просмотр всей библиотеки");
        System.out.println("\t2. Поиск по книгам");
        System.out.println("\t3. Предложить книгу");
        System.out.println("\t==================================");
    }
}
