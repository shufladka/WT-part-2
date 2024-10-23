package by.bsuir.domain;

/** Класс энергоэффективности */
public enum EnergyEfficiency {
    A_3_PLUS("A+++"),
    A_2_PLUS("A++"),
    A_PLUS("A+"),
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G");

    private String symbol;

    EnergyEfficiency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
