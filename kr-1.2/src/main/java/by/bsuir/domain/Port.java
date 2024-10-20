package by.bsuir.domain;

import java.util.List;

/**
 * Класс "Порт"
 */
public class Port {
    private int id;
    private String name;
    private List<Dock> docks;
    private Warehouse warehouse;

    public Port(int id, String name, List<Dock> docks, Warehouse warehouse) {
        this.id = id;
        this.name = name;
        this.docks = docks;
        this.warehouse = warehouse;
    }

    public Port() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dock> getDocks() {
        return docks;
    }

    public void setDocks(List<Dock> docks) {
        this.docks = docks;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", docks=" + docks +
                ", warehouse=" + warehouse +
                '}';
    }
}
