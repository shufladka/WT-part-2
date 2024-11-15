package by.bsuir.service.impl;

import by.bsuir.domain.ControlType;
import by.bsuir.domain.Dimensions;
import by.bsuir.domain.EnergyEfficiency;
import by.bsuir.domain.WashingMachine;
import by.bsuir.service.MenuService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Реализация интерфейса "Сервис меню"
 */
public class MenuServiceImpl implements MenuService {

    /**
     * Объект класса "Сканер" (для работы на чтение с устройства ввода)
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Ссылка на XML-документ (задано значение по умолчанию)
     */
    private String xmlFilePath = "E:\\IntellijIdeaProjects\\WT-part-2\\ipr-1-server\\src\\main\\resources\\wm.xml";

    /**
     * Тип парсера
     */
    private String parserType;

    /**
     * Метод для отображения главного меню в консоль
     * @param in Буфер введенного текста
     * @param out Печать форматированного представления объектов в поток текстового вывода
     */
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

            // Очистка буфера
            scanner.nextLine();

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

    /**
     * Метод, которому делегируется работа с получением пользовательского адреса XML-документа
     * @param scanner Экземпляр класса "Сканер"
     */
    private void addXmlFilePath(Scanner scanner) {
        System.out.print("\n\tEnter a link to the XML document: ");
        xmlFilePath = scanner.nextLine();
        System.out.println("\tLink to XML document saved: " + xmlFilePath);
    }

    /**
     * Метод для работы с выбором типа парсера
     * @param scanner Экземпляр класса "Сканер"
     */
    private void selectParserType(Scanner scanner) {
        System.out.println("\n\tEnter parser type (SAX, StAX, DOM): ");
        System.out.print("\tEnter a menu item (1-3) or 0 to exit: ");
        int parser = scanner.nextInt();

        // Очистка буфера
        scanner.nextLine();

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

    /**
     * Метод, которому делегирована работа по выводу текущих настроек в консоль
     */
    private void showCurrentSettings() {
        System.out.println("\n\t====== CURRENT SETTINGS ======");
        System.out.println("\tLink to XML document: " + (xmlFilePath != null ? xmlFilePath : "Not set"));
        System.out.println("\tParser type: " + (parserType != null ? parserType : "Not selected"));
        System.out.println("\t====================================");

        System.out.print("\n\tEnter any symbol to continue: ");

        // Очистка буфера
        scanner.nextLine();
    }

    /**
     * Метод для отправки данных на сервер
     * @param in Буфер введенного текста
     * @param out Печать форматированного представления объектов в поток текстового вывода
     */
    private void sendDataToServer(BufferedReader in, PrintWriter out) {
        try {
            if (xmlFilePath == null || parserType == null) {
                System.out.println("\tPlease set both the XML document link and parser type before sending.");
                return;
            }

            // Отправляем путь к XML-документу на сервер
            out.println(xmlFilePath);

            // Отправляем тип парсера на сервер
            out.println(parserType);

            // Ожидаем получения данных о стиральных машинах от сервера
            String input;
            while ((input = in.readLine()) != null) {

                // Выход из цикла при получении специальной строки
                if ("END_OF_RESPONSE".equals(input)) {
                    break;
                }

                // Десериализуем список объектов "Стиральная машина"
                List<WashingMachine> wm = deserialize(input);

                // Вывод списка объектов "Стиральная машина" в консоль
                displayWashingMachines(wm);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Метод для десериализации списка объектов "Стиральная машина"
     * @param serverResponse Полученный ответ от сервера
     * @return List of washing machines
     */
    private List<WashingMachine> deserialize(String serverResponse) {
        List<WashingMachine> washingMachines = new ArrayList<>();

        // Регулярное выражение для парсинга ответа от сервера в поиске стиральной машины
        String regex = "WashingMachine\\{id=(\\d+), brand='([^']+)', model='([^']+)', maxLoad=(\\d+\\.\\d+), dimensions=Dimensions\\{width=(\\d+\\.\\d+), height=(\\d+\\.\\d+), depth=(\\d+\\.\\d+), weight=(\\d+\\.\\d+)\\}, angularVelocity=(\\d+), amountOfPrograms=(\\d+), isConnectedToPhone=(true|false), energyEfficiency=([^,]+), controlType=([^}]+)\\}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(serverResponse);

        while (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));
            WashingMachine washingMachine = parseWashingMachine(matcher, id);
            washingMachines.add(washingMachine);
        }

        return washingMachines;
    }

    /**
     * Метод для парсинга ответа от сервера со структурой стиральной машины
     * @param matcher Строковое представление объекта
     * @param id Идентификатор стиральной машины
     * @return WashingMachine
     */
    private static WashingMachine parseWashingMachine(Matcher matcher, int id) {
        String brand = matcher.group(2);
        String model = matcher.group(3);

        Double maxLoad = Double.parseDouble(matcher.group(4));

        Double width = Double.parseDouble(matcher.group(5));
        Double height = Double.parseDouble(matcher.group(6));
        Double depth = Double.parseDouble(matcher.group(7));
        Double weight = Double.parseDouble(matcher.group(8));

        Dimensions dimensions = new Dimensions(width, height, depth, weight);

        int angularVelocity = Integer.parseInt(matcher.group(9));
        int amountOfPrograms = Integer.parseInt(matcher.group(10));

        boolean isConnectedToPhone = Boolean.parseBoolean(matcher.group(11));

        EnergyEfficiency energyEfficiency = EnergyEfficiency.valueOf(matcher.group(12));
        ControlType controlType = ControlType.valueOf(matcher.group(13));

        return new WashingMachine(id, brand, model, maxLoad,
                dimensions, angularVelocity, amountOfPrograms, isConnectedToPhone,
                energyEfficiency, controlType);
    }

    /**
     * Метод для вывода в консоль списка стиральных машин
     * @param machines Список стиральных машин
     */
    private void displayWashingMachines(List<WashingMachine> machines) {
        for (WashingMachine machine : machines) {
            System.out.println("\n\t" + machine.getId() + ". " + machine.getBrand() + " " + machine.getModel() + ";");
            System.out.println("\tMax. load: " + machine.getMaxLoad() + " kg;");
            System.out.println("\tAngular velocity: " + machine.getAngularVelocity() + " rpm;");
            System.out.println("\tNumber of programs: " + machine.getAmountOfPrograms() + ";");
            System.out.println("\tDimensions: " + machine.getDimensions().getHeight() + "x"
                                                + machine.getDimensions().getWidth() + "x"
                                                + machine.getDimensions().getDepth() + ";");
            System.out.println("\tConnection to smartphone: " + (machine.isConnectedToPhone() ? "Yes" : "No") + ";");
            System.out.println("\tEnergy efficiency class: " + machine.getEnergyEfficiency().getSymbol() + ";");
            System.out.println("\tControl type: " + machine.getControlType().getDescription() + ".");
        }
    }
}
