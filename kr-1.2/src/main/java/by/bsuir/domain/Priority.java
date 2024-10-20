package by.bsuir.domain;

/**
 * Перечисление "Приоритет"
 */
public enum Priority {
    HIGH("Высокий"),
    MEDIUM("Средний"),
    LOW("Низкий");

    private String name;

    Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
