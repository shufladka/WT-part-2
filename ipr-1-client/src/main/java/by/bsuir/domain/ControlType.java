package by.bsuir.domain;

/**
 * Перечисление "Тип управления"
 */
public enum ControlType {
    MECHANICAL("Механическое"),
    ELECTRONIC("Электронное"),
    TOUCH("Сенсорное");

    /**
     * Описание
     */
    private String description;

    /**
     * Конструктор перечисления "Тип управления"
     * @param description Описание
     */
    ControlType(String description) {
        this.description = description;
    }

    /**
     * Метод для получения описания
     * @return String
     */
    public String getDescription() {
        return description;
    }
}
