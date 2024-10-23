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
     * Пустой конструктор класса "Стиральная машина"
     */
    public WashingMachine() {
    }

    /**
     * Метод для задания идентификатора
     * @param id Идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод для задания марки
     * @param brand Марка
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Метод для задания модели
     * @param model Модель
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Метод для задания максимальной загрузки
     * @param maxLoad Максимальная загрузка
     */
    public void setMaxLoad(Double maxLoad) {
        this.maxLoad = maxLoad;
    }

    /**
     * Метод для задания габаритных размеров
     * @return Габаритные размеры
     */
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * Метод для задания габаритных размеров
     * @param dimensions Габаритные размеры
     */
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Метод для задания угловой скорости
     * @param angularVelocity Угловая скорость
     */
    public void setAngularVelocity(int angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    /**
     * Метод для задания количества программ
     * @param amountOfPrograms Количество программ
     */
    public void setAmountOfPrograms(int amountOfPrograms) {
        this.amountOfPrograms = amountOfPrograms;
    }

    /**
     * Метод для задания возможности подключения к смартфону
     * @param connectedToPhone Возможность подключения к смартфону
     */
    public void setConnectedToPhone(boolean connectedToPhone) {
        isConnectedToPhone = connectedToPhone;
    }

    /**
     * Метод для задания класса энергоэффективности
     * @param energyEfficiency Класс энергоэффективности
     */
    public void setEnergyEfficiency(EnergyEfficiency energyEfficiency) {
        this.energyEfficiency = energyEfficiency;
    }

    /**
     * Метод для задания типа управления
     * @param controlType Тип управления
     */
    public void setControlType(ControlType controlType) {
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
