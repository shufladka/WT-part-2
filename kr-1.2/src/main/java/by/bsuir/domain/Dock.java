package by.bsuir.domain;

/**
 * Класс "Причал"
 */
public class Dock {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * Имя
     */
    private String name;

    /**
     * Объект класса "Корабль"
     */
    private Ship ship;

    /**
     * Конструктор класса "Причал"
     * @param id Идентификатор причала
     * @param name Имя причала
     */
    public Dock(int id, String name) {
        this.id = id;
        this.name = name;
        this.ship = null;
    }

    /**
     * Метод для получения имени объекта
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для получения экземпляра класса "Корабль"
     * @return Ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Метод для задания корабля экземпляру класса "Причал"
     * @param ship Объект класса "Корабль"
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Метод для вывода параметров экземпляра класса "Причал"
     * @return String
     */
    @Override
    public String toString() {
        return "Dock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ship=" + ship +
                '}';
    }
}
