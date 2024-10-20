package by.bsuir.service.impl;

import by.bsuir.domain.*;
import by.bsuir.service.DispatcherService;
import by.bsuir.service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    private String MODE = "synchronized";

    /**
     * Метод для главного меню
     */
    @Override
    public void showMainMenu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        DispatcherService dispatcherService = new DispatcherServiceImpl();
        Warehouse warehouse = new Warehouse(1, "The biggest warehouse", 100000.0, 0.0);
        List<Dock> dockList = new ArrayList<>();
        List<Ship> shipList = new ArrayList<>();
        Port port = new Port(1, "Atlanta port", dockList, warehouse);

        while (true) {
            System.out.println("\n\t========== MAIN MENU ==========");
            System.out.println("\t1. Dock Menu");
            System.out.println("\t2. Ship Menu");
            System.out.println("\t3. Mode Selection");
            System.out.println("\t4. View Configuration");
            System.out.println("\t5. Launch Dispatch System");
            System.out.println("\t0. Exit");
            System.out.println("\t=================================");

            System.out.print("\n\tEnter a menu item (1-5) or 0 to exit: ");
            int selectedItem = scanner.nextInt();

            switch (selectedItem) {
                case 0:
                    return;
                case 1:
                    showDockMenu(dockList);
                    break;
                case 2:
                    showShipMenu(shipList);
                    break;
                case 3:
                    showModeMenu();
                    break;
                case 4:
                    showDispatcherConfig(port, warehouse, shipList, dockList);
                    break;
                case 5:
                    if (!dockList.isEmpty() && !shipList.isEmpty()) {

                        List<Thread> threadList = new ArrayList<>();

                        for (Ship ship : shipList) {
                            if (MODE.equals("synchronized")) {
                                Thread thread = new Thread(() -> dispatcherService.assignDockConc(port, ship));
                                threadList.add(thread);
                            }
                            else if (MODE.equals("concurrent")) {
                                Thread thread = new Thread(() -> dispatcherService.assignDockConc(port, ship));
                                threadList.add(thread);
                            }
                        }

                        for (Thread thread : threadList) {
                            thread.start();
                        }

                        for (Thread thread : threadList) {
                            thread.join();
                        }
                    }
                    break;
                default:
                    System.out.println("\tInvalid item, try again.");
                    break;
            }
        }
    }

    /**
     * Метод для отображения меню конфигурации причалов
     */
    private List<Dock> showDockMenu(List<Dock> dockList) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\t========== DOCK MENU ==========");
            System.out.println("\t1. Add a dock");
            System.out.println("\t2. Remove a dock");
            System.out.println("\t3. View dock information");
            System.out.println("\t0. Exit");
            System.out.println("\t=================================");

            System.out.print("\n\tEnter a menu item (1-3) or 0 to exit: ");
            int selectedItem = scanner.nextInt();

            switch (selectedItem) {
                case 0 -> {
                    return dockList;
                }
                case 1 -> {
                    int id = dockList != null ? dockList.size() : 0;
                    Dock dock = new Dock(id, id + "'st('th) dock");
                    assert dockList != null;
                    dockList.add(dock);
                    System.out.println("\tDock added: " + dock);
                }
                case 2 -> {
                    if (dockList != null && !dockList.isEmpty()) {
                        Dock removedDock = dockList.remove(dockList.size() - 1);
                        System.out.println("\tDock removed: " + removedDock);
                    } else {
                        System.out.println("\tNo docks available to remove.");
                    }
                }
                case 3 -> {
                    if (dockList != null && !dockList.isEmpty()) {
                        System.out.println("\n\t========== DOCK INFORMATION ==========");
                        dockList.forEach(dock -> System.out.println("\t" + dock));
                        System.out.println("\t=================================");
                    } else {
                        System.out.println("\tNo docks available.");
                    }
                }
                default -> System.out.println("\tInvalid item, try again.");
            }
        }
    }

    /**
     * Метод для отображения меню конфигурации кораблей
     */
    private List<Ship> showShipMenu(List<Ship> shipList) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\t========== SHIP MENU ==========");
            System.out.println("\t1. Add a ship");
            System.out.println("\t2. Remove a ship");
            System.out.println("\t3. View ship information");
            System.out.println("\t0. Exit");
            System.out.println("\t=================================");

            System.out.print("\n\tEnter a menu item (1-3) or 0 to exit: ");
            int selectedItem = scanner.nextInt();

            switch (selectedItem) {
                case 0 -> {
                    return shipList;
                }
                case 1 -> {
                    Ship ship = generateShip(shipList);
                    assert shipList != null;
                    shipList.add(ship);
                    System.out.println("\tShip added: " + ship);
                }
                case 2 -> {
                    if (shipList != null && !shipList.isEmpty()) {
                        Ship removedShip = shipList.remove(shipList.size() - 1);
                        System.out.println("\tShip removed: " + removedShip);
                    } else {
                        System.out.println("\tNo ships available to remove.");
                    }
                }
                case 3 -> {
                    if (shipList != null && !shipList.isEmpty()) {
                        System.out.println("\n\t========== SHIP INFORMATION ==========");
                        shipList.forEach(ship -> System.out.println("\t" + ship));
                        System.out.println("\t=================================");
                    } else {
                        System.out.println("\tNo ships available.");
                    }
                }
                default -> System.out.println("\tInvalid item, try again.");
            }
        }
    }

    /**
     * Метод для генерации объекта "Корабль"
     * @param shipList Список объектов "Корабль"
     * @return Ship
     */
    private Ship generateShip(List<Ship> shipList) {
        Random random = new Random();

        Double cargo = 200.00 + (5000.00 - 200.00) * random.nextDouble();

        Priority priority = null;
        int priorityValue = random.nextInt(3); // Получаем значение от 0 до 2
        switch (priorityValue) {
            case 0 -> priority = Priority.LOW;
            case 1 -> priority = Priority.MEDIUM;
            case 2 -> priority = Priority.HIGH;
        }

        int neededTime = 100 + random.nextInt(5000 - 100 + 1);

        int id = shipList != null ? shipList.size() : 0;
        Ship ship = new Ship(id, id + "'st('th) ship", cargo, priority, neededTime);
        return ship;
    }

    /**
     * Метод для отображения меню выбора режима работы диспетчерской системы
     */
    private void showModeMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\t========== MODE MENU ==========");
            System.out.println("\t1. 'Synchronized' mode");
            System.out.println("\t2. 'Concurrent' mode");
            System.out.println("\t0. Exit");
            System.out.println("\t=================================");

            System.out.print("\n\tEnter a menu item (1-2) or 0 to exit: ");
            int selectedItem = scanner.nextInt();

            switch (selectedItem) {
                case 0:
                    return;
                case 1:
                    MODE = "synchronized";
                    break;
                case 2:
                    MODE = "concurrent";
                    break;
                default:
                    System.out.println("\tInvalid item, try again.");
                    break;
            }
        }
    }

    /**
     * Метод для вывода конфигурации системы
     * @param port Объект класса "Порт"
     * @param warehouse Объект класса "Склад"
     * @param shipList Список объектов класса "Корабль"
     * @param dockList Список объектов класса "Причал"
     */
    private void showDispatcherConfig(Port port, Warehouse warehouse, List<Ship> shipList, List<Dock> dockList) {
        System.out.println("\n\t=================================");
        if (port != null) {
            System.out.println("\tPort: " + port);
        }
        if (warehouse != null) {
            System.out.println("\tWarehouse: " + warehouse);
        }
        if (shipList != null) {
            System.out.println("\tShips: " + shipList);
        }
        System.out.println("\tMode: " + MODE);
        if (dockList != null) {
            System.out.println("\tDocks: " + dockList);
        }
        System.out.println("\t=================================");
    }
}
