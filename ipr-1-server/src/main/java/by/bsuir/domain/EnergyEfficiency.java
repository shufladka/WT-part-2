package by.bsuir.domain;

/**
 * Перечисление "Класс энергоэффективности"
 */
public enum EnergyEfficiency {

    /**
     * A+++
     */
    A_3_PLUS("A+++"),

    /**
     * A++
     */
    A_2_PLUS("A++"),

    /**
     * A+
     */
    A_PLUS("A+"),

    /**
     * A
     */
    A("A"),

    /**
     * B
     */
    B("B"),

    /**
     * C
     */
    C("C"),

    /**
     * D
     */
    D("D"),

    /**
     * E
     */
    E("E"),

    /**
     * F
     */
    F("F"),

    /**
     * G
     */
    G("G");

    /**
     * Знак
     */
    private String symbol;

    /**
     * Конструктор перечисления "Класс энергоэффективности"
     * @param symbol Знак
     */
    EnergyEfficiency(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Метод для получения знака класса энергоэффективности
     * @return String
     */
    public String getSymbol() {
        return symbol;
    }
}
