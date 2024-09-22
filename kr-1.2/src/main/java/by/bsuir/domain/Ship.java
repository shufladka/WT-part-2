package by.bsuir.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ship {
    private int id;
    private String name;
    private Double cargo;
    private Dock dock;
    private Priority priority;
    private int neededTime;

    private static final Logger logger = LogManager.getLogger(Ship.class);

    public Ship(int id, String name, Double cargo, Priority priority, int neededTime) {
        this.id = id;
        this.name = name;
        this.cargo = cargo;
        this.dock = null;
        this.priority = priority;
        this.neededTime = neededTime;
    }

    public Ship() {}

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

    public Double getCargo() {
        return cargo;
    }

    public void setCargo(Double cargo) {
        this.cargo = cargo;
    }

    public Dock getDock() {
        return dock;
    }

    public void setDock(Dock dock) {
        if (dock != null) {
            logger.info("The ship №{} added to the dock \"{}\"", this.id, dock.getName());
        } else {
            logger.info("The ship №{} left the port", this.id);
        }
        this.dock = dock;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(int neededTime) {
        this.neededTime = neededTime;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cargo=" + cargo +
                ", dock=" + dock +
                ", priority=" + priority +
                ", neededTime=" + neededTime +
                '}';
    }
}
