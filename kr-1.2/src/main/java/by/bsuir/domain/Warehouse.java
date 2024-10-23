package by.bsuir.domain;

/**
 * Класс "Склад"
 */
public class Warehouse {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * Имя
     */
    private String name;

    /**
     * Максимальная емкость
     */
    private Double maxCapacity;

    /**
     * Текущая емкость
     */
    private Double currentCapacity;

    /**
     * Конструктор класса "Склад"
     * @param id Идентификатор
     * @param name Имя
     * @param maxCapacity Максимальная емкость
     * @param currentCapacity Текущая емкость
     */
    public Warehouse(int id, String name, Double maxCapacity, Double currentCapacity) {
        this.id = id;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    /**
     * Метод для получения имени склада
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для получения текущей емкости склада
     * @return Double
     */
    public Double getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * Метод для задания текущей емкости склада
     * @param currentCapacity Текущая емкость
     */
    public void setCurrentCapacity(Double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    /**
     * Метод для вывода параметров экземпляра класса "Склад"
     * @return String
     */
    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", currentCapacity=" + currentCapacity +
                '}';
    }
}
