package by.bsuir.service.impl;

import by.bsuir.service.MenuService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    private String xmlFilePath;
    private String parserType;

    @Override
    public void showMainMenu(BufferedReader in, PrintWriter out) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\t====== ГЛАВНОЕ МЕНЮ ======");
            System.out.println("\t1. Добавить ссылку на XML-документ");
            System.out.println("\t2. Выбрать тип парсера (SAX, StAX, DOM)");
            System.out.println("\t3. Показать текущие настройки");
            System.out.println("\t0. Выйти");
            System.out.println("\t==================================");

            System.out.print("\n\tВведите пункт меню (1-3) или 0 для выхода: ");
            int selectedPage = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (selectedPage) {
                case 0:
                    System.out.println("\tВыход из приложения.");
                    return;
                case 1:
                    addXmlFilePath(scanner);
                    break;
                case 2:
                    selectParserType(scanner);
                    break;
                case 3:
                    showCurrentSettings();
                    break;
                default:
                    System.out.println("\tНеверный номер страницы, попробуйте снова.");
                    break;
            }
        }
    }

    private void addXmlFilePath(Scanner scanner) {
        System.out.print("\n\tВведите ссылку на XML-документ: ");
        xmlFilePath = scanner.nextLine();
        System.out.println("\tСсылка на XML-документ сохранена: " + xmlFilePath);
    }

    private void selectParserType(Scanner scanner) {
        System.out.print("\n\tВведите тип парсера (SAX, StAX, DOM): ");
        parserType = scanner.nextLine().toUpperCase();

        if (parserType.equals("SAX") || parserType.equals("STAX") || parserType.equals("DOM")) {
            System.out.println("\tТип парсера выбран: " + parserType);
        } else {
            System.out.println("\tНеверный тип парсера. Попробуйте снова.");
            parserType = null;
        }
    }

    private void showCurrentSettings() {
        System.out.println("\n\t====== ТЕКУЩИЕ НАСТРОЙКИ ======");
        System.out.println("\tСсылка на XML-документ: " + (xmlFilePath != null ? xmlFilePath : "Не задано"));
        System.out.println("\tТип парсера: " + (parserType != null ? parserType : "Не выбран"));
        System.out.println("\t==================================\n");
    }
}
