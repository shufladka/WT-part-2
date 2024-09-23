package by.bsuir.domain;

public class Dock {
    private int id;
    private String name;
    private Ship ship;

    public Dock(int id, String name) {
        this.id = id;
        this.name = name;
        this.ship = null;
    }

    public Dock() {}

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

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public String toString() {
        return "Dock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ship=" + ship +
                '}';
    }
}
