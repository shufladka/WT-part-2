package by.bsuir.service.impl;

import by.bsuir.service.MenuService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    Scanner scanner = new Scanner(System.in);
    private String xmlFilePath;
    private String parserType;

    @Override
    public void showMainMenu(BufferedReader in, PrintWriter out) {

        while (true) {
            System.out.println("\n\t====== MAIN MENU ======");
            System.out.println("\t1. Add a link to an XML document");
            System.out.println("\t2. Select a parser type (SAX, StAX, DOM)");
            System.out.println("\t3. Show current settings");
            System.out.println("\t4. Send XML document and parser type to server");
            System.out.println("\t0. Exit");
            System.out.println("\t=================================");

            System.out.print("\n\tEnter a menu item (1-4) or 0 to exit: ");
            int selectedPage = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (selectedPage) {
                case 0:
                    System.out.println("\tExiting the application.");
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
                case 4:
                    sendDataToServer(in, out);
                    break;
                default:
                    System.out.println("\tInvalid page number, try again.");
                    break;
            }
        }
    }

    private void addXmlFilePath(Scanner scanner) {
        System.out.print("\n\tEnter a link to the XML document: ");
        xmlFilePath = scanner.nextLine();
        System.out.println("\tLink to XML document saved: " + xmlFilePath);
    }

    private void selectParserType(Scanner scanner) {
        System.out.println("\n\tEnter parser type (SAX, StAX, DOM): ");
        System.out.print("\tEnter a menu item (1-3) or 0 to exit: ");
        int parser = scanner.nextInt();
        scanner.nextLine();  // Очистка буфера

        switch (parser) {
            case 0:
                System.out.println("\tExiting the choosing.");
                return;
            case 1:
                parserType = "SAX";
                System.out.println("\tWas chosen SAX parser.");
                break;
            case 2:
                parserType = "StAX";
                System.out.println("\tWas chosen StAX parser.");
                break;
            case 3:
                parserType = "DOM";
                System.out.println("\tWas chosen DOM parser.");
                break;
            default:
                System.out.println("\tInvalid page number, try again.");
                break;
        }
    }

    private void showCurrentSettings() {
        System.out.println("\n\t====== CURRENT SETTINGS ======");
        System.out.println("\tLink to XML document: " + (xmlFilePath != null ? xmlFilePath : "Not set"));
        System.out.println("\tParser type: " + (parserType != null ? parserType : "Not selected"));
        System.out.println("\t====================================");

        System.out.print("\n\tEnter any symbol to continue: ");
        scanner.nextLine();  // Очистка буфера
    }

    // Новая функция для отправки данных на сервер
    private void sendDataToServer(BufferedReader in, PrintWriter out) {
        try {
            if (xmlFilePath == null || parserType == null) {
                System.out.println("\tPlease set both the XML document link and parser type before sending.");
                return;
            }

            // Отправляем путь к XML и тип парсера на сервер
            out.println(xmlFilePath);
            out.println(parserType);

            String input;
            while ((input = in.readLine()) != null) {

                // Выход из цикла при получении специального сообщения
                if ("END_OF_RESPONSE".equals(input)) {
                    break;
                }
                System.out.println(input);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
