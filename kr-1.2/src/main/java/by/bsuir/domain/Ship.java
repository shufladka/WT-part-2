package by.bsuir.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс "Корабль"
 */
public class Ship {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * Имя
     */
    private String name;

    /**
     * Вес перевозимого груза
     */
    private Double cargo;

    /**
     * Объект класса "Причал"
     */
    private Dock dock;

    /**
     * Объект перечисления "Приоритет"
     */
    private Priority priority;

    /**
     * Объект перечисления "Операция"
     */
    private Operation operation;

    /**
     * Необходимое для загрузки/разгрузки время
     */
    private int neededTime;

    /**
     * Объект класса "Логгер"
     */
    private static final Logger logger = LogManager.getLogger(Ship.class);

    /**
     * Конструктор класса "Корабль"
     * @param id Идентификатор
     * @param name Имя
     * @param cargo Вес перевозимого груза
     * @param operation Тип операции
     * @param priority Приоритет
     * @param neededTime Необходимое для разгрузки время
     */
    public Ship(int id, String name, Double cargo, Operation operation, Priority priority, int neededTime) {
        this.id = id;
        this.name = name;
        this.cargo = cargo;
        this.dock = null;
        this.operation = operation;
        this.priority = priority;
        this.neededTime = neededTime;
    }

    /**
     * Пустой конструктор класса "Корабль"
     */
    public Ship() {}

    /**
     * Метод для получения имени корабля
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для получения веса перевозимого кораблем груза
     * @return Double
     */
    public Double getCargo() {
        return cargo;
    }

    /**
     * Метод для задания кораблю причала
     * @param dock Назначаемый причал
     */
    public void setDock(Dock dock) {
        if (dock != null) {
            logger.info("[{}] [{} tons] The ship №{} added to the dock \"{}\"", this.operation.getName(), this.cargo, this.id, dock.getName());
        } else {
            logger.info("[{}] The ship №{} left the port", this.operation.getFinishName(), this.id);
        }
        this.dock = dock;
    }

    /**
     * Метод для получения типа операции
     * @return Operation
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Метод для получения времени, необходимого для разгрузки корабля
     * @return int
     */
    public int getNeededTime() {
        return neededTime;
    }

    /**
     * Метод для вывода параметров экземпляра класса "Корабль"
     * @return String
     */
    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cargo=" + cargo +
                ", dock=" + dock +
                ", operation=" + operation +
                ", priority=" + priority +
                ", neededTime=" + neededTime +
                '}';
    }
}
