package by.bsuir.service.impl;

import by.bsuir.domain.Dock;
import by.bsuir.domain.Port;
import by.bsuir.domain.Ship;
import by.bsuir.domain.Warehouse;
import by.bsuir.service.DispatcherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DispatcherServiceImpl implements DispatcherService {

    private static final Logger logger = LogManager.getLogger(DispatcherServiceImpl.class);
    private final List<Ship> waitingShips = new ArrayList<>();

    /**
     * Метод для назначения причала текущему кораблю
     * @param port Объект класса "Порт"
     * @param ship Объект класса "Корабль"
     */
    @Override
    public void assignDockToShip(Port port, Ship ship) {
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
                setUpdatedCapacityValue(port, ship.getCargo());
                logger.info("Current capacity: {}", port.getWarehouse().getCurrentCapacity());

                ship.setDock(null);
                availableDock.setShip(null);
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }

        processWaitingShips(port);

    }

    /**
     * Метод для обработки очереди ожидающих кораблей
     * @param port Объект класса "Порт"
     */
    public synchronized void processWaitingShips(Port port) {
        while (!waitingShips.isEmpty()) {
            try {
                Thread.sleep(1000);
                Dock availableDock = getAvailableDock(port);

                if (availableDock != null) {
                    Ship ship = waitingShips.remove(0); // Берем первый корабль из очереди
                    assignDockToShip(port, ship); // Пытаемся назначить ему причал
                }
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Метод для логирования общей информации
     */
    @Override
    public void logCommonInformation() {}

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
                availableDock = dock; // Вернуть первый доступный причал
                break;
            }
        }
        return availableDock;
    }

    /**
     * Метод для обновления информации о текущем состоянии склада
     * @param port Объект класса "Порт"
     * @param cargo Объем груза, полученного от корабля
     */
    private void setUpdatedCapacityValue(Port port, Double cargo) {
        Warehouse warehouse = port.getWarehouse();
        Double currentCapacity = warehouse.getCurrentCapacity();
        warehouse.setCurrentCapacity(currentCapacity + cargo);
    }
}
