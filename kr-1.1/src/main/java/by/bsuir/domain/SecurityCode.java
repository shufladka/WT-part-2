package by.bsuir.domain;

/**
 * Перечисление кодов для удобной работы с системой аутентификации
 */
public enum SecurityCode {
    DENIED("Отклонен", "Текущая операция не может быть совершена"),
    ALLOWED("Разрешен", "Текущая операция может быть совершена");

    private String name;
    private String description;

    SecurityCode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
