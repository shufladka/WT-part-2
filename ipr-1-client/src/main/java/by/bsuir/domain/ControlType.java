package by.bsuir.domain;

/**
 * Перечисление "Тип управления"
 */
public enum ControlType {
    MECHANICAL("MECHANICAL"),
    ELECTRONIC("ELECTRONIC"),
    TOUCH("TOUCH");

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
