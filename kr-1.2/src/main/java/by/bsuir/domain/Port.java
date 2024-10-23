package by.bsuir.domain;

import java.util.List;

/**
 * Класс "Порт"
 */
public class Port {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * Имя
     */
    private String name;

    /**
     * Список причалов
     */
    private List<Dock> docks;

    /**
     * Склад
     */
    private Warehouse warehouse;

    /**
     * Конструктор класса "Порт"
     * @param id Идентификатор
     * @param name Имя
     * @param docks Список причалов
     * @param warehouse Склад
     */
    public Port(int id, String name, List<Dock> docks, Warehouse warehouse) {
        this.id = id;
        this.name = name;
        this.docks = docks;
        this.warehouse = warehouse;
    }

    /**
     * Метод для получения имени объекта класса "Порт"
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для получения списка причалов
     * @return List of docks
     */
    public List<Dock> getDocks() {
        return docks;
    }

    /**
     * Метод для получения назначенного объекта "Склад"
     * @return Warehouse
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Метод для вывода параметров экземпляра класса "Порт"
     * @return String
     */
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
