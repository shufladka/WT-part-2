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
     * Метод для получения идентификатора
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Метод для получения марки
     * @return String
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Метод для получения модели
     * @return String
     */
    public String getModel() {
        return model;
    }

    /**
     * Метод для получения максимальной загрузки
     * @return Double
     */
    public Double getMaxLoad() {
        return maxLoad;
    }

    /**
     * Метод для получения габаритных размеров
     * @return Double
     */
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * Метод для получения угловой скорости
     * @return int
     */
    public int getAngularVelocity() {
        return angularVelocity;
    }

    /**
     * Метод для получения количества программ
     * @return int
     */
    public int getAmountOfPrograms() {
        return amountOfPrograms;
    }

    /**
     * Метод для получения наличия соединения с телефоном
     * @return boolean
     */
    public boolean isConnectedToPhone() {
        return isConnectedToPhone;
    }

    /**
     * Метод для получения класса энергоэффективности
     * @return EnergyEfficiency
     */
    public EnergyEfficiency getEnergyEfficiency() {
        return energyEfficiency;
    }

    /**
     * Метод для получения типа управления
     * @return ControlType
     */
    public ControlType getControlType() {
        return controlType;
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
