package by.bsuir.domain;

/** Тип управления */
public enum ControlType {
    MECHANICAL("Механическое"),
    ELECTRONIC("Электронное"),
    TOUCH("Сенсорное");

    private String description;

    ControlType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
