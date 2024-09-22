package by.bsuir.service;

import by.bsuir.domain.Dock;
import by.bsuir.domain.Port;
import by.bsuir.domain.Ship;

import java.util.List;

public interface DispatcherService {
    void assignDockToShip(Port port, Ship ship);
    void logCommonInformation();
    List<Dock> getFreeDocks(Port port);
    boolean isDockAvailable(Dock dock);
}
