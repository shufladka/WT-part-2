package by.bsuir.domain;

/**
 * Перечисление "Тип книги"
 */
public enum BookType {
    PAPER("Бумажная книга"),
    ELECTRONIC("Электронная книга");

    private final String description;

    BookType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
