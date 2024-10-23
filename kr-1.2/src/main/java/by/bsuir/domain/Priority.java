package by.bsuir.domain;

/**
 * Перечисление "Приоритет"
 */
public enum Priority {

    /**
     * Тип приоритета "Высокий"
     */
    HIGH("Высокий"),

    /**
     * Тип приоритета "Средний"
     */
    MEDIUM("Средний"),

    /**
     * Тип приоритета "Низкий"
     */
    LOW("Низкий");

    /**
     * Имя
     */
    private String name;

    /**
     * Конструктор перечисления "Приоритет"
     * @param name Имя приоритета
     */
    Priority(String name) {
        this.name = name;
    }

    /**
     * Метод для получения имени объекта класса "Имя"
     * @return String
     */
    public String getName() {
        return name;
    }
}
