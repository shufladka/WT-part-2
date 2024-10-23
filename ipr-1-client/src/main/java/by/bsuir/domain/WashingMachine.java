package by.bsuir.domain;

/**
 * Класс "Стиральная машина"
 */
public class WashingMachine {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * Марка
     */
    private String brand;

    /**
     * Модель
     */
    private String model;

    /**
     * Максимальная загрузка
     */
    private Double maxLoad;

    /**
     * Габариты
     */
    private Dimensions dimensions;

    /**
     * Угловая скорость
     */
    private int angularVelocity;

    /**
     * Количество программ стирки
     */
    private int amountOfPrograms;

    /**
     * Возможно ли подключение к смартфону
     */
    private boolean isConnectedToPhone;

    /**
     * Класс энергоэффективности
     */
    private EnergyEfficiency energyEfficiency;

    /**
     * Тип управления
     */
    private ControlType controlType;

    /**
     * Конструктор класса "Стиральная машина"
     * @param id Идентификатор
     * @param brand Марка
     * @param model Модель
     * @param maxLoad Максимальная загрузка
     * @param dimensions Габариты
     * @param angularVelocity Угловая скорость
     * @param amountOfPrograms Количество программ стирки
     * @param isConnectedToPhone Возможно ли подключение к смартфону
     * @param energyEfficiency Класс энергоэффективности
     * @param controlType Тип управления
     */
    public WashingMachine(int id, String brand, String model, Double maxLoad,
                          Dimensions dimensions, int angularVelocity, int amountOfPrograms,
                          boolean isConnectedToPhone, EnergyEfficiency energyEfficiency,
                          ControlType controlType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.maxLoad = maxLoad;
        this.dimensions = dimensions;
        this.angularVelocity = angularVelocity;
        this.amountOfPrograms = amountOfPrograms;
        this.isConnectedToPhone = isConnectedToPhone;
        this.energyEfficiency = energyEfficiency;
        this.controlType = controlType;
    }

    /**
     * Метод для вывода информации о стиральной машине в консоль
     * @return String
     */
    @Override
    public String toString() {
        return "WashingMachine{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", maxLoad=" + maxLoad +
                ", dimensions=" + dimensions +
                ", angularVelocity=" + angularVelocity +
                ", amountOfPrograms=" + amountOfPrograms +
                ", isConnectedToPhone=" + isConnectedToPhone +
                ", energyEfficiency=" + energyEfficiency +
                ", controlType=" + controlType +
                '}';
    }
}
