package by.bsuir.service.impl;

import by.bsuir.domain.*;
import by.bsuir.service.DispatcherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Реализация "Сервиса системы диспетчеризации"
 */
public class DispatcherServiceImpl implements DispatcherService {

    private final Logger loggerDispatcher = LogManager.getLogger(DispatcherServiceImpl.class);
    private final List<Ship> waitingShips = new ArrayList<>();

    /**
     * Блокировка для доступа к причалам (concurrent)
     */
    private final Lock dockLock = new ReentrantLock();

    /**
     * Метод для назначения причала текущему кораблю (synchronized)
     * @param port Объект класса "Порт"
     * @param ship Объект класса "Корабль"
     */
    @Override
    public void assignDockSync(Port port, Ship ship) {
        Dock availableDock;

        try {
            synchronized (this) {
                Thread.sleep(1000);

                availableDock = getAvailableDock(port);

                if (availableDock != null) {
                    ship.setDock(availableDock);
                    availableDock.setShip(ship);
                } else {
                    waitingShips.add(ship);
                }
            }

            if (availableDock != null) {
                Thread.sleep(ship.getNeededTime());

                // Обновляем информацию о складе
                setUpdatedCapacityValue(port, ship.getCargo(), ship.getOperation());
                loggerDispatcher.info("[SYNCHRONIZED] Current capacity: {}", port.getWarehouse().getCurrentCapacity());

                ship.setDock(null);
                availableDock.setShip(null);
            }
        } catch (InterruptedException e) {
            loggerDispatcher.error(e);
        }

        // Обрабатываем очередь ожидающих кораблей
        processWaitingSync(port);

    }

    /**
     * Метод для обработки очереди ожидающих кораблей (synchronized)
     * @param port Объект класса "Порт"
     */
    public synchronized void processWaitingSync(Port port) {
        while (!waitingShips.isEmpty()) {
            try {
                Thread.sleep(1000);
                Dock availableDock = getAvailableDock(port);

                if (availableDock != null) {
                    Ship ship = waitingShips.remove(0);
                    assignDockSync(port, ship);
                }
            } catch (InterruptedException e) {
                loggerDispatcher.error(e);
            }
        }
    }

    /**
     * Метод для назначения причала текущему кораблю (concurrent)
     * @param port Объект класса "Порт"
     * @param ship Объект класса "Корабль"
     */
    @Override
    public void assignDockConc(Port port, Ship ship) {
        Dock availableDock;

        try {

            // Блокируем доступ к причалам
            dockLock.lock();
            try {

                // Симулируем задержку
                Thread.sleep(1000);

                // Получаем доступный причал
                availableDock = getAvailableDock(port);

                if (availableDock != null) {
                    ship.setDock(availableDock);
                    availableDock.setShip(ship);
                } else {

                    // Добавляем в очередь, если нет свободного причала
                    waitingShips.add(ship);
                }
            } finally {

                // Разблокируем поток
                dockLock.unlock();
            }

            // Если назначен причал, обрабатываем работу с судном
            if (availableDock != null) {

                // Время, необходимое для обработки корабля
                Thread.sleep(ship.getNeededTime());

                // Обновляем информацию о складе
                setUpdatedCapacityValue(port, ship.getCargo(), ship.getOperation());
                loggerDispatcher.info("[CONCURRENT] Current capacity: {}", port.getWarehouse().getCurrentCapacity());

                // Освобождаем причал
                ship.setDock(null);
                availableDock.setShip(null);
            }
        } catch (InterruptedException e) {
            loggerDispatcher.error(e);
        }

        // Обрабатываем очередь ожидающих кораблей
        processWaitingConc(port);
    }

    /**
     * Метод для обработки очереди ожидающих кораблей
     * @param port Объект класса "Порт"
     */
    public void processWaitingConc(Port port) {
        while (!waitingShips.isEmpty()) {
            try {

                // Симулируем задержку
                Thread.sleep(1000);

                // Блокируем доступ к причалам
                dockLock.lock();
                try {

                    // Получаем доступный причал
                    Dock availableDock = getAvailableDock(port);

                    if (availableDock != null && !waitingShips.isEmpty()) {

                        // Берем первый корабль из очереди
                        Ship ship = waitingShips.remove(0);

                        // Пытаемся назначить ему причал
                        assignDockConc(port, ship);
                    }
                } finally {

                    // Разблокируем поток
                    dockLock.unlock();
                }
            } catch (InterruptedException e) {
                loggerDispatcher.error(e);

                // Восстанавливаем статус прерывания
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Метод для получения списка свободных причалов
     * @param port Объект класса "Порт"
     * @return List of docks
     */
    @Override
    public List<Dock> getFreeDocks(Port port) {
        List<Dock> docks = new ArrayList<>();
        port.getDocks().forEach(dock -> {
            if (isDockAvailable(dock)) {
                docks.add(dock);
            }
        });
        return docks;
    }

    /**
     * Метод для проверки на доступность причала
     * @param dock Объект класса "Причал"
     * @return true || false
     */
    @Override
    public boolean isDockAvailable(Dock dock) {
        return dock.getShip() == null;
    }

    /**
     * Метод для получения свободного причала
     * @param port Объект класса "Порт"
     * @return free dock
     */
    private Dock getAvailableDock(Port port) {
        Dock availableDock = null;
        List<Dock> freeDocks = getFreeDocks(port);
        for (Dock dock : freeDocks) {
            if (isDockAvailable(dock)) {
                availableDock = dock;
                break;
            }
        }
        return availableDock;
    }

    /**
     * Метод для обновления информации о текущем состоянии склада
     * @param port Объект класса "Порт"
     * @param cargo Объем груза, полученного от корабля
     * @param operation Тип операции
     */
    private void setUpdatedCapacityValue(Port port, Double cargo, Operation operation) {
        Warehouse warehouse = port.getWarehouse();
        Double currentCapacity = warehouse.getCurrentCapacity();
        if (operation.equals(Operation.UNLOADING)) {
            warehouse.setCurrentCapacity(currentCapacity + cargo);
        } else if (operation.equals(Operation.LOADING)) {
            warehouse.setCurrentCapacity(currentCapacity - cargo);
        }
    }
}
