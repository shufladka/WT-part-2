package by.bsuir.service.impl;

import by.bsuir.domain.Dock;
import by.bsuir.domain.Port;
import by.bsuir.domain.Ship;
import by.bsuir.domain.Warehouse;
import by.bsuir.service.DispatcherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DispatcherServiceImpl implements DispatcherService {

    private static final Logger logger = LogManager.getLogger(DispatcherServiceImpl.class);
    private final List<Ship> waitingShips = new ArrayList<>();


    // Назначить причал текущему кораблю
    @Override
    public void assignDockToShip(Port port, Ship ship) {

        Dock availableDock = null;
        try {
            synchronized (this) {
                Thread.sleep(1000);

                availableDock = getAvailableDock(port);

                if (availableDock != null) {
                    ship.setDock(availableDock);
                    availableDock.setShip(ship);
                } else {
//                    System.out.println("is not available");
                    waitingShips.add(ship);
                }
            }

//            Thread.sleep(ship.getNeededTime());
//            ship.setDock(null);

            if (availableDock != null) {
                Thread.sleep(ship.getNeededTime());

                // Обновляем информацию о складе
                setUpdatedCapacityValue(port, ship.getCargo());
                logger.info("Current capacity: " + port.getWarehouse().getCurrentCapacity());

                ship.setDock(null);
                availableDock.setShip(null);
            }

        } catch (InterruptedException e) {
//            e.printStackTrace();
        }

        processWaitingShips(port);

    }

    public synchronized void processWaitingShips(Port port) {
        while (!waitingShips.isEmpty()) {

            try {
                Thread.sleep(1000);
                Dock availableDock = getAvailableDock(port);

                if (availableDock != null) {
                    Ship ship = waitingShips.remove(0); // Берем первый корабль из очереди
                    assignDockToShip(port, ship); // Пытаемся назначить ему причал
                } else {
//                    System.out.println("is not available");
                }
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
    }

    // Логирование текущего состояния порта
    @Override
    public void logCommonInformation() {

    }

    // Получить список свободных причалов
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

    // Проверить, свободен ли причал
    @Override
    public boolean isDockAvailable(Dock dock) {
        return dock.getShip() == null;
    }

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

    // Обновить информацию о текущем состоянии склада
    private void setUpdatedCapacityValue(Port port, Double cargo) {
        Warehouse warehouse = port.getWarehouse();
        Double currentCapacity = warehouse.getCurrentCapacity();
        warehouse.setCurrentCapacity(currentCapacity + cargo);
    }
}
