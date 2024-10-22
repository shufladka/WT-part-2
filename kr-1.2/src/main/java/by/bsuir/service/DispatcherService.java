package by.bsuir.service;

import by.bsuir.domain.Dock;
import by.bsuir.domain.Port;
import by.bsuir.domain.Ship;

import java.util.List;

public interface DispatcherService {
    void assignDockSync(Port port, Ship ship);
    void assignDockConc(Port port, Ship ship);
    List<Dock> getFreeDocks(Port port);
    boolean isDockAvailable(Dock dock);
}
