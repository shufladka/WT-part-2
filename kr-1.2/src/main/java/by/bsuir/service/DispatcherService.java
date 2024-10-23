package by.bsuir.service;

import by.bsuir.domain.Dock;
import by.bsuir.domain.Port;
import by.bsuir.domain.Ship;

import java.util.List;

/**
 * Интерфейс "Сервис системы диспетчеризации"
 */
public interface DispatcherService {

    /**
     * Метод для назначения причала текущему кораблю (synchronized)
     * @param port Объект класса "Порт"
     * @param ship Объект класса "Корабль"
     */
    void assignDockSync(Port port, Ship ship);

    /**
     * Метод для назначения причала текущему кораблю (concurrent)
     * @param port Объект класса "Порт"
     * @param ship Объект класса "Корабль"
     */
    void assignDockConc(Port port, Ship ship);

    /**
     * Метод для получения списка свободных причалов
     * @param port Объект класса "Порт"
     * @return List of docks
     */
    List<Dock> getFreeDocks(Port port);

    /**
     * Метод для проверки на доступность причала
     * @param dock Объект класса "Причал"
     * @return true || false
     */
    boolean isDockAvailable(Dock dock);
}
