package by.bsuir.domain;

/** Стиральная машина */
public class WashingMachine {
    private int id;
    private String brand;
    private String model;
    private Double maxLoad;
    private Dimensions dimensions;      // Габариты
    private int angularVelocity;        // Угловая скорость
    private int amountOfPrograms;       // Количество программ стирки
    private boolean isConnectedToPhone; // Возможно ли подключение к смартфону
    private EnergyEfficiency energyEfficiency;  // Класс энергоэффективности
    private ControlType controlType;    // Тип управления

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(Double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public int getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(int angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public int getAmountOfPrograms() {
        return amountOfPrograms;
    }

    public void setAmountOfPrograms(int amountOfPrograms) {
        this.amountOfPrograms = amountOfPrograms;
    }

    public boolean isConnectedToPhone() {
        return isConnectedToPhone;
    }

    public void setConnectedToPhone(boolean connectedToPhone) {
        isConnectedToPhone = connectedToPhone;
    }

    public EnergyEfficiency getEnergyEfficiency() {
        return energyEfficiency;
    }

    public void setEnergyEfficiency(EnergyEfficiency energyEfficiency) {
        this.energyEfficiency = energyEfficiency;
    }

    public ControlType getControlType() {
        return controlType;
    }

    public void setControlType(ControlType controlType) {
        this.controlType = controlType;
    }

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
